package com.epam.xmlparsing.factory;

import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.dom.DomParser;
import com.epam.xmlparsing.parser.jaxb.JaxbParser;
import com.epam.xmlparsing.parser.sax.SaxParser;

import java.util.Optional;

public class ParserFactory {

    public Optional<Parser> getParser(String parserName){
        if(parserName != null){
            Parser parser = null;
            switch (parserName) {
                case "DomParser":
                    parser = new DomParser();
                    break;
                case "JaxbParser":
                    parser = new JaxbParser();
                    break;
                case "SaxParser":
                    parser = new SaxParser();
                    break;
            }
            return Optional.ofNullable(parser);
        }
        return Optional.empty();
    }
}
