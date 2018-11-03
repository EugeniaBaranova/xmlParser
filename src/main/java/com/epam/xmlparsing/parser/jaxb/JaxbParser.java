package com.epam.xmlparsing.parser.jaxb;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Flowers;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {

    private Logger logger = Logger.getLogger(JaxbParser.class);

    public List<Flower> parse(String fileName) throws XmlParserException {

        try {
            //https://examples.javacodegeeks.com/core-java/xml/bind/jaxb-unmarshal-example/

            File file = new File(fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(Flowers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Flowers flowers = (Flowers) jaxbUnmarshaller.unmarshal(file);
            return flowers.getFlowers();

        } catch (JAXBException e) {
            logger.error("", e);
            throw new XmlParserException(e.getMessage(), e);
        }
    }
}
