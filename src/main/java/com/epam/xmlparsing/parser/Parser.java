package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.parser.exception.XmlParserException;

import java.util.List;

public interface Parser {

    List<Flower> parse(String filePath) throws XmlParserException;

}
