package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;
import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;
import com.epam.xmlparsing.parser.FlowerFieldFiller;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static com.epam.xmlparsing.parser.FlowerFieldNames.*;


public class XmlHandler extends DefaultHandler {

    private static final String FLOWERS = "Flowers";
    private static final String ROSE = "Rose";
    private static final String PEONY = "Peony";

    private List<String> flowerNames = new ArrayList<>(Arrays.asList(ROSE, PEONY));
    private List<String> fieldNames = new ArrayList<>(Arrays.asList(
            NAME, ORIGIN, SOIL, COLOR, LENGTH, HELIOPHYTE, OPTIMAL_TEMPERATURE, WITH_SPIKES, MULTIROWED));
    private String currentFieldName;

    private Map<String, String> currentFieldsValues;

    private List<Flower> flowers;

    private Flower currentFlower;

    private FlowerFieldFiller flowerFieldFiller = new FlowerFieldFiller();

    public XmlHandler() {
        flowers = new ArrayList<>();
        currentFieldsValues = new HashMap<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != null) {
            if (!FLOWERS.equals(qName)) {
                if (attributes != null) {
                    if (NAME.equals(attributes.getLocalName(0))) {

                        switch (qName) {
                            case ROSE:
                                currentFlower = new Rose();
                                break;
                            case PEONY:
                                currentFlower = new Peony();
                                break;
                        }

                        String value = attributes.getValue(0);
                        currentFlower.setName(value);
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
                flowers.add(currentFlower);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentFieldName != null) {
            switch (currentFieldName) {
                case ORIGIN:
                    currentFlower.setOrigin(
                            Origin.valueOf(value));
                    break;
                case SOIL:
                    currentFlower.setSoil(
                            Soil.valueOf(value));
                    break;
                case COLOR:
                    currentFlower.setColor(
                            Color.valueOf(value));
                    break;
                case LENGTH:
                    currentFlower.setLength(
                            Integer.valueOf(value));
                    break;
                case HELIOPHYTE:
                    currentFlower.setHeliophyte(
                            Boolean.valueOf(value));
                    break;
                case OPTIMAL_TEMPERATURE:
                    currentFlower.setOptimalTemperature(
                            Integer.valueOf(value));
                    break;
                case WITH_SPIKES:
                    Rose currentRose = (Rose) currentFlower;
                    currentRose.setWithSpikes(
                            Boolean.valueOf(value));
                    break;
                case MULTIROWED:
                    Peony currentPeony = (Peony) currentFlower;
                    currentPeony.setMultirowed(
                            Boolean.valueOf(value));
                    break;
            }
        }
        currentFieldName = null;
    }

    /*@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != null) {
            if (!FLOWERS.equals(qName)) {
                if (attributes != null) {
                    if (NAME.equals(attributes.getLocalName(0))) {
                        String value = attributes.getValue(0);
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
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentFieldName != null) {

            for (String fieldName : fieldNames) {
                if (fieldName.equals(currentFieldName)) {
                    currentFieldsValues.put(fieldName, value);
                }
            }
        }
    }*/


}
