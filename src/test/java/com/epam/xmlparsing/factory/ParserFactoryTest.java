package com.epam.xmlparsing.factory;

import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.dom.DomParser;
import com.epam.xmlparsing.parser.jaxb.JaxbParser;
import com.epam.xmlparsing.parser.sax.SaxParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ParserFactoryTest {

    private static final String DOM_PARSER = "DomParser";
    private static final String SAX_PARSER = "SaxParser";
    private static final String JAXB_PARSER = "JaxbParser";

    private ParserFactory parserFactory = new ParserFactory();

    @Test
    public void shouldGetDomParserWhenGivenNameOfDomParser() {
        //when
        Optional<Parser> result = parserFactory.getParser(DOM_PARSER);
        //then
        Assert.assertTrue(result.isPresent());
        Parser parser = result.get();
        Assert.assertEquals(parser.getClass(), DomParser.class);
    }

    @Test
    public void shouldGetSaxParserWhenGivenNameOfSaxParser() {
        //when
        Optional<Parser> result = parserFactory.getParser(SAX_PARSER);
        //then
        Assert.assertTrue(result.isPresent());
        Parser parser = result.get();
        Assert.assertEquals(parser.getClass(), SaxParser.class);
    }

    @Test
    public void shouldGetJaxbParserWhenGivenNameOfJaxbParser() {
        //when
        Optional<Parser> result = parserFactory.getParser(JAXB_PARSER);
        //then
        Assert.assertTrue(result.isPresent());
        Parser parser = result.get();
        Assert.assertEquals(parser.getClass(), JaxbParser.class);
    }

    @Test
    public void shouldGetEmptyOptionalWhenGivenNull() {
        //when
        Optional<Parser> result = parserFactory.getParser(null);
        //then
        Assert.assertFalse(result.isPresent());
    }

}
