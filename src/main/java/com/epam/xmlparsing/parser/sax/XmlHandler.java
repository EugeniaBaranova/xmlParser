package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;
import com.epam.xmlparsing.parser.FlowerFieldFiller;
import com.epam.xmlparsing.parser.dom.DomParser;
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
                if (attributes == null) {
                    for (String fieldName : fieldNames) {
                        if (fieldName.equals(qName)) {
                            currentFieldName = qName;
                        }
                    }
                } else {
                    if (NAME.equals(attributes.getLocalName(0))) {
                        String value = attributes.getValue(0);
                        currentFieldsValues.put(NAME, value);
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
    }


}
