package com.epam.xmlparsing.entity.greenhouse;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Flower")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Flower {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlElement(name = "origin")
    protected Origin origin;
    @XmlElement(name = "soil")
    protected Soil soil;
    @XmlElement(name = "color")
    protected Color color;
    @XmlElement(name = "length")
    protected int length;
    @XmlElement(name = "heliophyte")
    protected boolean heliophyte;
    @XmlElement(name = "optimal-temperature")
    protected int optimalTemperature;

    public Flower() {

    }

    public Flower(String name,
                  Origin origin,
                  Soil soil,
                  Color color,
                  int length,
                  boolean heliophyte,
                  int optimalTemperature) {
        this.name = name;
        this.origin = origin;
        this.soil = soil;
        this.color = color;
        this.length = length;
        this.heliophyte = heliophyte;
        this.optimalTemperature = optimalTemperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHeliophyte() {
        return heliophyte;
    }

    public void setHeliophyte(boolean heliophyte) {
        this.heliophyte = heliophyte;
    }

    public int getOptimalTemperature() {
        return optimalTemperature;
    }

    public void setOptimalTemperature(int optimalTemperature) {
        this.optimalTemperature = optimalTemperature;
    }
}
