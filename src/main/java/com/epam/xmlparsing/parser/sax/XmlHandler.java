package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.parser.FlowerFieldFiller;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static com.epam.xmlparsing.parser.entity.FlowerFieldNames.*;
import static com.epam.xmlparsing.parser.entity.FlowerNames.PEONY;
import static com.epam.xmlparsing.parser.entity.FlowerNames.ROSE;


public class XmlHandler extends DefaultHandler {

    private static final int ZERO_INDEX = 0;
    private static final String EMPTY_STRING = "";

    private static final String FLOWERS = "Flowers";

    private List<String> flowerNames = new ArrayList<>(Arrays.asList(ROSE, PEONY));
    private List<String> fieldNames = new ArrayList<>(Arrays.asList(
            NAME, ORIGIN, SOIL, COLOR, LENGTH, HELIOPHYTE, OPTIMAL_TEMPERATURE, WITH_SPIKES, MULTIROWED));

    private String currentFieldName;

    private Map<String, String> currentFieldsValues;

    private List<Flower> flowers;

    private FlowerFieldFiller flowerFieldFiller;

    public XmlHandler() {
        flowers = new ArrayList<>();
        currentFieldsValues = new HashMap<>();
        flowerFieldFiller = new FlowerFieldFiller();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != null) {
            if (!FLOWERS.equals(qName)) {
                if (attributes != null) {
                    if (NAME.equals(attributes.getLocalName(ZERO_INDEX))) {
                        String value = attributes.getValue(ZERO_INDEX);
                        currentFieldsValues.put(NAME, value);
                    }
                    for (String fieldName : fieldNames) {
                        if (fieldName.equals(qName)) {
                            currentFieldName = qName;
                        }
                    }
                }
            }
        }
    }



    @Override
    public void endElement(String uri, String localName, String qName) {
        for (String flowerName : flowerNames) {
            if (flowerName.equals(qName)) {

                Optional<Flower> flowerOptional = flowerFieldFiller.fillFields(qName, currentFieldsValues);

                if (flowerOptional.isPresent()) {
                    Flower currentFlower = flowerOptional.get();
                    flowers.add(currentFlower);
                    currentFieldsValues.clear();
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentFieldName != null && !value.equals(EMPTY_STRING)) {

            for (String fieldName : fieldNames) {
                if (fieldName.equals(currentFieldName)) {
                    currentFieldsValues.putIfAbsent(fieldName, value);
                }
            }
        }
    }


}
