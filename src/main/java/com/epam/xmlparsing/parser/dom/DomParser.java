package com.epam.xmlparsing.parser.dom;

import com.epam.xmlparsing.entity.*;
import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;
import com.epam.xmlparsing.parser.FilePathGetter;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DomParser implements Parser {

    private Logger logger = Logger.getLogger(DomParser.class);

    private FilePathGetter filePathGetter = new FilePathGetter();

    public List<Flower> parse(String fileName) throws XmlParserException {

        List<Flower> flowerList = new ArrayList<>();

        try {
            Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);
            if (filePathOptional.isPresent()) {

                String filePath = filePathOptional.get();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(filePath);

                document.getDocumentElement().normalize();

                Element documentElement = document.getDocumentElement();
                if (documentElement.hasChildNodes()) {
                    NodeList flowers = document.getChildNodes();
                    for (int i = 0; i < flowers.getLength(); i++) {

                        Node flower = flowers.item(i);

                        if (flower.getNodeType() == Node.ELEMENT_NODE) {

                            String flowerName = flower.getNodeName();
                            if (Rose.class
                                    .getSimpleName()
                                    .equals(flowerName)) {
                               /* Rose rose = new Rose();
                                Map<String, String> valuesOfFields = new HashMap<>();
                                NodeList childNodesOfFlower = flower.getChildNodes();
                                for (int j = 0; j < childNodesOfFlower.getLength(); j++) {
                                    Node field = childNodesOfFlower.item(j);

                                    String fieldName = field.getNodeName();
                                    String fieldValue = field.getTextContent();

                                    valuesOfFields.put(fieldName, fieldValue);
                                }

                                rose.setName(valuesOfFields.get("name"));
                                rose.setOrigin(Origin.valueOf(valuesOfFields.get("origin")));
                                rose.setSoil(Soil.valueOf(valuesOfFields.get("soil")));
                                rose.setColor(Color.valueOf(valuesOfFields.get("color")));
                                rose.setLength(Integer.valueOf(valuesOfFields.get("length")));
                                rose.setHeliophyte(Boolean.valueOf(valuesOfFields.get("heliophyte")));
                                rose.setOptimalTemperature(Integer.valueOf(valuesOfFields.get("optimal-temperature")));
                                rose.setWithSpikes(Boolean.valueOf(valuesOfFields.get("with-spikes")));

                                flowerList.add(rose);*/

                            }

                            if (Peony.class
                                    .getSimpleName()
                                    .equals(flowerName)) {
                                /*Peony peony = new Peony();
                                Map<String, String> valuesOfFields = new HashMap<>();
                                NodeList childNodesOfFlower = flower.getChildNodes();
                                for (int j = 0; j < childNodesOfFlower.getLength(); j++) {
                                    Node field = childNodesOfFlower.item(j);

                                    String fieldName = field.getNodeName();
                                    String fieldValue = field.getTextContent();

                                    valuesOfFields.put(fieldName, fieldValue);
                                }

                                peony.setName(valuesOfFields.get("name"));
                                peony.setOrigin(Origin.valueOf(valuesOfFields.get("origin")));
                                peony.setSoil(Soil.valueOf(valuesOfFields.get("soil")));
                                peony.setColor(Color.valueOf(valuesOfFields.get("color")));
                                peony.setLength(Integer.valueOf(valuesOfFields.get("length")));
                                peony.setHeliophyte(Boolean.valueOf(valuesOfFields.get("heliophyte")));
                                peony.setOptimalTemperature(Integer.valueOf(valuesOfFields.get("optimal-temperature")));
                                peony.setMultirowed(Boolean.valueOf(valuesOfFields.get("multirowed")));

                                flowerList.add(peony);*/
                            }

                        }

                    }
                }
            }
        } catch (ParserConfigurationException e) {
            logger.error("", e);
            throw new XmlParserException(e);
        } catch (SAXException e) {
            logger.error("", e);
            throw new XmlParserException(e);
        } catch (IOException e) {
            logger.error("", e);
            throw new XmlParserException(e);
        }
        return flowerList;
    }
}
