package com.epam.xmlparsing.parser.dom;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class DomParserTest {

    private static final String FILE_NAME = "input.xml";
    private static final String NONEXISTENT_FILE_NAME = "xnjksdkv";

    private DomParser domParser = new DomParser();

    @Test
    public void shouldParseWhenFileExist() throws XmlParserException {
        //when
        List<Flower> result = domParser.parse(FILE_NAME);
        //then
        Assert.assertThat(result.size(), is(4));
    }

    @Test
    public void shouldNotParseWhenFileNotExist() throws XmlParserException {
        //when
        List<Flower> result = domParser.parse(NONEXISTENT_FILE_NAME);
        //then
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldNotParseWhenGivenNull() throws XmlParserException {
        //when
        List<Flower> result = domParser.parse(null);
        //then
        Assert.assertTrue(result.isEmpty());
    }


}
