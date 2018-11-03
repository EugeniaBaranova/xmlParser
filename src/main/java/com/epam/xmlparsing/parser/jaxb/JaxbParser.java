package com.epam.xmlparsing.parser.jaxb;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Greenhouse;
import com.epam.xmlparsing.parser.FilePathGetter;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class JaxbParser implements Parser {

    private Logger logger = Logger.getLogger(JaxbParser.class);

    private FilePathGetter filePathGetter = new FilePathGetter();

    public List<Flower> parse(String fileName) throws XmlParserException {

        try {
            Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);
            if(filePathOptional.isPresent()){

                //https://examples.javacodegeeks.com/core-java/xml/bind/jaxb-unmarshal-example/

                File file = new File(filePathOptional.get());

                JAXBContext jaxbContext = JAXBContext.newInstance(Greenhouse.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Greenhouse greenhouse = (Greenhouse) jaxbUnmarshaller.unmarshal(file);
                return greenhouse.getFlowers();
            }

        } catch (JAXBException e) {
            logger.error("", e);
            throw new XmlParserException(e.getMessage(), e);
        }
        return null;
    }
}
