package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;
import com.epam.xmlparsing.parser.dom.DomParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class XmlHandler extends DefaultHandler {

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

    private Flower flower;

    private List<String> flowerNames = new ArrayList<>(Arrays.asList(ROSE, PEONY));
    private List<String> fieldNames = new ArrayList<>(Arrays.asList(NAME, ORIGIN, SOIL, COLOR, LENGTH, HELIOPHYTE,
            OPTIMAL_TEMPERATURE, WITH_SPIKES, MULTIROWED));
    private String currentFieldName;
    private Map<String, String> fieldsValues;

    private List<Flower> flowers;

    public XmlHandler() {
        flowers = new ArrayList<>();
        fieldsValues = new HashMap<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != null) {
            if (attributes != null) {
                if (NAME.equals(attributes.getLocalName(0))) {
                    String value = attributes.getValue(0);
                    fieldsValues.put(NAME, value);
                }
            }
            for (String fieldName : fieldNames) {
                if (fieldName.equals(qName)) {
                    currentFieldName = qName;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        for (String flowerName : flowerNames) {
            if (flowerName.equals(qName)) {

                //TODO separate class for method

                DomParser domParser = new DomParser();

                switch (qName) {
                    case ROSE:
                        flower = new Rose();

                        domParser.fillCommonFields(flower, fieldsValues);

                        Rose rose = (Rose) flower;
                        String withSpikes = fieldsValues.get(WITH_SPIKES);
                        rose.setWithSpikes(
                                Boolean.valueOf(withSpikes));

                        break;
                    case PEONY:
                        flower = new Peony();

                        domParser.fillCommonFields(flower, fieldsValues);

                        Peony peony = (Peony) flower;
                        String multirowed = fieldsValues.get(MULTIROWED);
                        peony.setMultirowed(
                                Boolean.valueOf(multirowed));

                        break;
                }

                flowers.add(flower);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentFieldName != null) {
            for (String fieldName : fieldNames) {
                if (fieldName.equals(currentFieldName)) {
                    fieldsValues.put(fieldName, value);
                }
            }
        }
    }


}
