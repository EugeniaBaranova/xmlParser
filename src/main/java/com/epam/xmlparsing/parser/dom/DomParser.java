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
import java.util.*;

public class DomParser implements Parser {

    private Logger logger = Logger.getLogger(DomParser.class);

    private static final String ROSE = "Rose";
    private static final String PEONY = "Peony";

    private static final String NAME = "name";
    private static final String ORIGIN = "origin";
    private static final String SOIL = "soil";
    private static final String COLOR = "color";
    private static final String LENGTH = "length";
    private static final String HELIOPHYTE = "heliophyte";
    private static final String OPTIMAL_TEMPERATURE = "optimal-temperature";
    private static final String WITH_SPIKES = "with-spikes";
    private static final String MULTIROWED = "multirowed";

    private FilePathGetter filePathGetter = new FilePathGetter();

    private List<String> flowerNames = new ArrayList<>(Arrays.asList(ROSE, PEONY));

    public List<Flower> parse(String fileName) throws XmlParserException {

        try {
            Optional<String> filePathOptional = filePathGetter.getFilePath(fileName);
            if (filePathOptional.isPresent()) {

                List<Flower> flowerList = new ArrayList<>();

                String filePath = filePathOptional.get();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(filePath);

                Element root = document.getDocumentElement();
                document.normalize();

                for (String flowerName : flowerNames) {
                    NodeList flowers = root.getElementsByTagName(flowerName);

                    for (int i = 0; i < flowers.getLength(); i++) {

                        Node node = flowers.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {

                            Map<String, String> fieldsValues = new HashMap<>();

                            if (node.hasChildNodes()) {
                                NodeList childNodes = node.getChildNodes();

                                for (int j = 0; j < childNodes.getLength(); j++) {

                                    Node field = childNodes.item(j);

                                    String fieldName = field.getNodeName();
                                    String fieldValue = field.getTextContent();

                                    fieldsValues.put(fieldName, fieldValue);
                                }
                            }

                            if (node.hasAttributes()) {
                                NamedNodeMap attributes = node.getAttributes();

                                Node attribute = attributes.getNamedItem(NAME);

                                String name = attribute.getNodeName();
                                String value = attribute.getNodeValue();

                                fieldsValues.put(name, value);
                            }

                            String nodeName = node.getNodeName();

                            Optional<Flower> flowerOptional = fillFields(nodeName, fieldsValues);

                            if (flowerOptional.isPresent()) {
                                Flower flower = flowerOptional.get();
                                flowerList.add(flower);
                            }

                        }

                    }

                }
                return flowerList;
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("", e);
            throw new XmlParserException(e);
        }
        return new ArrayList<>(0);
    }

    public Optional<Flower> fillFields(String nodeName, Map<String, String> fieldsValues) {
        if (nodeName != null) {
            Flower flower = null;
            switch (nodeName) {
                case ROSE:
                    flower = new Rose();

                    fillCommonFields(flower, fieldsValues);

                    Rose rose = (Rose) flower;
                    String withSpikes = fieldsValues.get(WITH_SPIKES);
                    rose.setWithSpikes(
                            Boolean.valueOf(withSpikes));

                    break;
                case PEONY:
                    flower = new Peony();

                    fillCommonFields(flower, fieldsValues);

                    Peony peony = (Peony) flower;
                    String multirowed = fieldsValues.get(MULTIROWED);
                    peony.setMultirowed(
                            Boolean.valueOf(multirowed));

                    break;
            }
            return Optional.ofNullable(flower);
        }
        return Optional.empty();
    }

    public void fillCommonFields(Flower flower, Map<String, String> fieldsValues) {

        String name = fieldsValues.get(NAME);
        String origin = fieldsValues.get(ORIGIN);
        String soil = fieldsValues.get(SOIL);
        String color = fieldsValues.get(COLOR);
        String length = fieldsValues.get(LENGTH);
        String heliophyte = fieldsValues.get(HELIOPHYTE);
        String optimalTemperature = fieldsValues.get(OPTIMAL_TEMPERATURE);


        flower.setName(name);
        flower.setOrigin(
                Origin.valueOf(origin));
        flower.setSoil(
                Soil.valueOf(soil));
        flower.setColor(
                Color.valueOf(color));
        flower.setLength(
                Integer.valueOf(length));
        flower.setHeliophyte(
                Boolean.valueOf(heliophyte));
        flower.setOptimalTemperature(
                Integer.valueOf(optimalTemperature));

    }


}
