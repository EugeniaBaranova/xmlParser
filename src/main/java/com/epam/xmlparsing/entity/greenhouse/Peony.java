package com.epam.xmlparsing.entity.greenhouse;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({Flower.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Peony")
public class Peony extends Flower {

    @XmlElement(name = "multirowed")
    private boolean multirowed;

    public Peony() {

    }

    public Peony(String name,
                 Origin origin,
                 Soil soil,
                 Color color,
                 int length,
                 boolean heliophyte,
                 int optimalTemperature,
                 boolean multirowed) {
        super(name, origin, soil, color, length, heliophyte, optimalTemperature);
        this.multirowed = multirowed;
    }

    public boolean isMultirowed() {
        return multirowed;
    }

    public void setMultirowed(boolean multirowed) {
        this.multirowed = multirowed;
    }
}
