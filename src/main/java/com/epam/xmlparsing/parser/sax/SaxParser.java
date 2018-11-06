package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.parser.FilePathGetter;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaxParser implements Parser {

    private Logger logger = Logger.getLogger(SaxParser.class);

    private FilePathGetter filePathGetter = new FilePathGetter();
    private XmlHandler xmlHandler = new XmlHandler();

    public List<Flower> parse(String fileName) throws XmlParserException {
        Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);
        if (filePathOptional.isPresent()) {

            String filePath = filePathOptional.get();

            try {


                XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setContentHandler(xmlHandler);

                xmlReader.parse(filePath);
                return xmlHandler.getFlowers();
            } catch (SAXException | IOException e) {
                logger.error("", e);
                throw new XmlParserException(e);
            }

        }
        return new ArrayList<>(0);
    }
}
