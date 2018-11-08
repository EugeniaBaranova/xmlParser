package com.epam.xmlparsing.validator;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class ValidatorErrorHandler extends DefaultHandler {

    private Logger logger = Logger.getLogger("com.epam.xmlparsing.validator");
    private String log;
    private ValidationResult validationResult;

    public ValidatorErrorHandler(String log, ValidationResult validationResult) {
        this.log = log;
        this.validationResult = validationResult;
    }

    public void warning(SAXParseException e) {
        setUp(log);
        logger.warn(getLineAddress(e) + "-" + e.getMessage());
        validationResult.setErrorMessage(ErrorMessages.INVALID_FILE);
    }

    public void error(SAXParseException e) {
        setUp(log);
        logger.error(getLineAddress(e) + "-" + e.getMessage());
        validationResult.setErrorMessage(ErrorMessages.INVALID_FILE);

    }

    public void fatalError(SAXParseException e) {
        setUp(log);
        logger.fatal(getLineAddress(e) + "-" + e.getMessage());
        validationResult.setErrorMessage(ErrorMessages.INVALID_FILE);
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + ":" + e.getColumnNumber();
    }

    private void setUp(String log) {
        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), log));
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
