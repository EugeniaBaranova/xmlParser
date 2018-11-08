package com.epam.xmlparsing.parser.jaxb;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Flowers;
import com.epam.xmlparsing.utils.FilePathGetter;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JaxbParser implements Parser {

    private Logger logger = Logger.getLogger(JaxbParser.class);

    private FilePathGetter filePathGetter = new FilePathGetter();

    public List<Flower> parse(String fileName) throws XmlParserException {

        Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);

        try {

            if(filePathOptional.isPresent()){
                String filePath = filePathOptional.get();

                File file = new File(filePath);

                JAXBContext jaxbContext = JAXBContext.newInstance(Flowers.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Flowers flowers = (Flowers) jaxbUnmarshaller.unmarshal(file);
                return flowers.getFlowers();

            }

        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
            throw new XmlParserException(e.getMessage(), e);
        }

        return new ArrayList<>(0);
    }
}
