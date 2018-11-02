package com.epam.xml.parser;

import com.epam.xml.entity.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DomParser implements Parser{

    private Logger logger = Logger.getLogger(DomParser.class);

    public List<Flower> parse(String fileName) {

        List<Flower> flowerList = new ArrayList<>();

        try {
            Optional<String> filePath = getFilePath(fileName);
            if(filePath.isPresent()) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(filePath.get());

                document.getDocumentElement().normalize();
                if (document.hasChildNodes()) {
                    NodeList flowers = document.getChildNodes();
                    for (int i = 0; i < flowers.getLength(); i++) {

                        Node flower = flowers.item(i);

                        if (flower.getNodeType() == Node.ELEMENT_NODE) {

                            String flowerName = flower.getNodeName();
                            if(Rose.class
                                    .getSimpleName()
                                    .equals(flowerName)){
                                Rose rose = new Rose();
                                Map<String, String> valuesOfFields = new HashMap<>();
                                NodeList childNodesOfFlower = flower.getChildNodes();
                                for (int j = 0; j < childNodesOfFlower.getLength(); j++) {
                                    Node field = childNodesOfFlower.item(j);

                                    String fieldName = field.getNodeName();
                                    String fieldValue = field.getTextContent();

                                    valuesOfFields.put(fieldName, fieldValue);
                                }

                                rose.setName(valuesOfFields.get("name"));
                                rose.setOrigin(valuesOfFields.get("origin"));
                                rose.setSoil(Soil.valueOf(valuesOfFields.get("soil")));
                                rose.setColor(Color.valueOf(valuesOfFields.get("color")));
                                rose.setLength(Integer.valueOf(valuesOfFields.get("length")));
                                rose.setHeliophyte(Boolean.valueOf(valuesOfFields.get("heliophyte")));
                                rose.setOptimalTemperature(Integer.valueOf(valuesOfFields.get("optimalTemperature")));
                                rose.setWithSpikes(Boolean.valueOf(valuesOfFields.get("withSpikes")));

                                flowerList.add(rose);

                            }

                            if(Peony.class
                                    .getSimpleName()
                                    .equals(flowerName)){
                                Peony peony = new Peony();
                                Map<String, String> valuesOfFields = new HashMap<>();
                                NodeList childNodesOfFlower = flower.getChildNodes();
                                for (int j = 0; j < childNodesOfFlower.getLength(); j++) {
                                    Node field = childNodesOfFlower.item(j);

                                    String fieldName = field.getNodeName();
                                    String fieldValue = field.getTextContent();

                                    valuesOfFields.put(fieldName, fieldValue);
                                }

                                peony.setName(valuesOfFields.get("name"));
                                peony.setOrigin(valuesOfFields.get("origin"));
                                peony.setSoil(Soil.valueOf(valuesOfFields.get("soil")));
                                peony.setColor(Color.valueOf(valuesOfFields.get("color")));
                                peony.setLength(Integer.valueOf(valuesOfFields.get("length")));
                                peony.setHeliophyte(Boolean.valueOf(valuesOfFields.get("heliophyte")));
                                peony.setOptimalTemperature(Integer.valueOf(valuesOfFields.get("optimalTemperature")));
                                peony.setMultirowed(Boolean.valueOf(valuesOfFields.get("multirowed")));

                                flowerList.add(peony);
                            }

                        }

                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flowerList;
    }

    private Optional<String> getFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null){
            URL resource = classLoader.getResource(fileName);
            if(resource != null){
                String path = resource.getPath();
                return Optional.of(path);
            }
        }
        return Optional.empty();
    }

}
