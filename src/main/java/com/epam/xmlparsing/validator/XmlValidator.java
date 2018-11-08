package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.utils.FilePathGetter;
import com.epam.xmlparsing.validator.exeption.ValidationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XmlValidator implements Validator {

    private Logger logger = Logger.getLogger(XmlValidator.class);

    private String schemaName;

    private Schema schema;
    private String logname = "logs/log.txt";
    private String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private SchemaFactory schemaFactory = SchemaFactory.newInstance(language);

    private FilePathGetter filePathGetter = new FilePathGetter();

    public XmlValidator(String schemaName) {
        this.schemaName = schemaName;
    }

    @Override
    public ValidationResult validate(String fileName) throws ValidationException {
        ValidationResult validationResult = new ValidationResult();
        if (fileName == null) {
            validationResult.setErrorMessage(
                    ErrorMessages.NULL_FILE_NAME);
        } else {
            Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);
            if (filePathOptional.isPresent()) {
                String filePath = filePathOptional.get();

                Optional<String> schemaPathOptional = filePathGetter.getFilePath(schemaName);
                if (schemaPathOptional.isPresent()) {
                    String schemaPath = schemaPathOptional.get();

                    try {

                        checkUsingXsd(validationResult, filePath, schemaPath);
                        if (validationResult.getErrorMessage() == null) {
                            validationResult.setValidity(true);
                        } else {
                            validationResult.setValidity(false);
                        }
                        return validationResult;

                    } catch (SAXException | ParserConfigurationException | IOException e) {
                        logger.error(e.getMessage(), e);
                        throw new ValidationException(e.getMessage(), e);
                    }
                } else {
                    validationResult.setErrorMessage(
                            ErrorMessages.NONEXISTENT_SCHEMA);
                }

            } else {
                validationResult.setErrorMessage(
                        ErrorMessages.NONEXISTENT_FILE);
            }
        }
        return validationResult;
    }

    private void checkUsingXsd(ValidationResult validationResult, String filePath, String schemaPath)
            throws SAXException, ParserConfigurationException, IOException {
        File schemaFile = new File(schemaPath);
        schema = schemaFactory.newSchema(schemaFile);
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setSchema(schema);

        SAXParser parser = parserFactory.newSAXParser();

        parser.parse(filePath, new ValidatorErrorHandler(logname, validationResult));
    }
}
