package com.epam.xml.entity;

public abstract class Flower {

    protected String name;
    protected String origin;
    protected Soil soil;
    protected Color color;
    protected int length;
    protected boolean heliophyte;
    protected int optimalTemperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
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
