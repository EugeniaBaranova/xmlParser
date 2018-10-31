package com.epam.xml.parser;

import com.epam.xml.entity.Flower;

import java.util.List;

public interface Parser {

    List<Flower> parse(String filePath);

}
