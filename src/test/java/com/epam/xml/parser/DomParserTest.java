package com.epam.xml.parser;

import com.epam.xml.entity.Flower;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class DomParserTest {

    private static final String FILE_NAME = "testGreenHouse.xml";
    private static final String NONEXISTENT_FILE_NAME = "xnjksdkv";

    private DomParser domParser = new DomParser();

    @Test
    public void shouldParseWhenFileExist() {
        //when
        List<Flower> result = domParser.parse(FILE_NAME);
        //then
        Assert.assertThat(result.size(), is(4));
    }


}
