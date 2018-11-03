package com.epam.xmlparsing.entity.greenhouse;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({Flower.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Rose")
public class Rose extends Flower {

    @XmlElement(name = "with-spikes")
    private boolean withSpikes;

    public Rose() {

    }

    public Rose(String name,
                Origin origin,
                Soil soil,
                Color color,
                int length,
                boolean heliophyte,
                int optimalTemperature,
                boolean withSpikes) {
        super(name, origin, soil, color, length, heliophyte, optimalTemperature);
        this.withSpikes = withSpikes;
    }

    public boolean isWithSpikes() {
        return withSpikes;
    }

    public void setWithSpikes(boolean withSpikes) {
        this.withSpikes = withSpikes;
    }
}
