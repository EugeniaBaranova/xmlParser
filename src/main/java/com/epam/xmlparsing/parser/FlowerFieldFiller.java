package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;
import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;

import java.util.Map;
import java.util.Optional;

import static com.epam.xmlparsing.parser.entity.FlowerFieldNames.*;
import static com.epam.xmlparsing.parser.entity.FlowerNames.PEONY;
import static com.epam.xmlparsing.parser.entity.FlowerNames.ROSE;

public class FlowerFieldFiller {

    public Optional<Flower> fillFields(String flowerName, Map<String, String> fieldsValues) {
        if (flowerName != null && fieldsValues != null) {
            Flower flower = null;
            switch (flowerName) {
                case ROSE:
                    flower = new Rose();

                    Rose rose = (Rose) flower;
                    String withSpikes = fieldsValues.get(WITH_SPIKES);
                    rose.setWithSpikes(
                            Boolean.valueOf(withSpikes));
                    break;
                case PEONY:
                    flower = new Peony();

                    Peony peony = (Peony) flower;
                    String multirowed = fieldsValues.get(MULTIROWED);
                    peony.setMultirowed(
                            Boolean.valueOf(multirowed));
                    break;
            }
            fillCommonFields(flower, fieldsValues);
            return Optional.ofNullable(flower);
        }
        return Optional.empty();
    }

    private void fillCommonFields(Flower flower, Map<String, String> fieldsValues) {
        if (flower != null && fieldsValues != null) {
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
}
