package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.entity.Color;
import com.epam.xmlparsing.entity.Origin;
import com.epam.xmlparsing.entity.Soil;
import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.entity.greenhouse.Peony;
import com.epam.xmlparsing.entity.greenhouse.Rose;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class SaxParserTest {

    private static final String FILE_NAME = "input.xml";
    private static final String NONEXISTENT_FILE_NAME = "xnjksdkv";

    private static final String NAME_NEW_DOWN = "New Dawn";
    private static final String NAME_AKRON = "Akron";
    private static final String NAME_SORBET = "Sorbet";

    private SaxParser saxParser = new SaxParser();

    @Test
    public void shouldParseWhenFileExist() throws XmlParserException {
        //given
        SaxParser saxParser = new SaxParser();
        //when
        List<Flower> result = saxParser.parse(FILE_NAME);
        //then
        Assert.assertThat(result.size(), is(4));

        Flower firstFlower = result.get(0);
        Assert.assertEquals(firstFlower.getClass(), Rose.class);
        Rose firstRose = (Rose) firstFlower;
        Assert.assertThat(firstRose.getName(),
                is(NAME_NEW_DOWN));
        Assert.assertThat(firstRose.getOrigin(),
                is(Origin.CHINA));
        Assert.assertThat(firstRose.getSoil(),
                is(Soil.CHERNOZEMS));
        Assert.assertThat(firstRose.getColor(),
                is(Color.RED));
        Assert.assertThat(firstRose.getLength(), is(10));
        Assert.assertTrue(firstRose.isHeliophyte());
        Assert.assertThat(firstRose.getOptimalTemperature(), is(22));
        Assert.assertTrue(firstRose.isWithSpikes());

        Flower secondFlower = result.get(1);
        Assert.assertEquals(secondFlower.getClass(), Rose.class);
        Rose secondRose = (Rose) secondFlower;
        Assert.assertThat(secondRose.getName(),
                is(NAME_NEW_DOWN));
        Assert.assertThat(secondRose.getOrigin(),
                is(Origin.CHINA));
        Assert.assertThat(secondRose.getSoil(),
                is(Soil.PODZOL));
        Assert.assertThat(secondRose.getColor(),
                is(Color.YELLOW));
        Assert.assertThat(secondRose.getLength(), is(10));
        Assert.assertTrue(secondRose.isHeliophyte());
        Assert.assertThat(secondRose.getOptimalTemperature(), is(22));
        Assert.assertFalse(secondRose.isWithSpikes());


        Flower thirdFlower = result.get(2);
        Assert.assertEquals(thirdFlower.getClass(), Peony.class);
        Peony thirdPeony = (Peony) thirdFlower;
        Assert.assertThat(thirdPeony.getName(),
                is(NAME_AKRON));
        Assert.assertThat(thirdPeony.getOrigin(),
                is(Origin.CHINA));
        Assert.assertThat(thirdPeony.getSoil(),
                is(Soil.CHERNOZEMS));
        Assert.assertThat(thirdPeony.getColor(),
                is(Color.RED));
        Assert.assertThat(thirdPeony.getLength(), is(12));
        Assert.assertTrue(thirdPeony.isHeliophyte());
        Assert.assertThat(thirdPeony.getOptimalTemperature(), is(22));
        Assert.assertFalse(thirdPeony.isMultirowed());

        Flower forthFlower = result.get(3);
        Assert.assertEquals(forthFlower.getClass(), Peony.class);
        Peony forthPeony = (Peony) forthFlower;
        Assert.assertThat(forthPeony.getName(),
                is(NAME_SORBET));
        Assert.assertThat(forthPeony.getOrigin(),
                is(Origin.CHINA));
        Assert.assertThat(forthPeony.getSoil(),
                is(Soil.PODZOL));
        Assert.assertThat(forthPeony.getColor(),
                is(Color.RED));
        Assert.assertThat(forthPeony.getLength(), is(8));
        Assert.assertTrue(forthPeony.isHeliophyte());
        Assert.assertThat(forthPeony.getOptimalTemperature(), is(22));
        Assert.assertTrue(forthPeony.isMultirowed());
    }

    @Test
    public void shouldNotParseWhenFileNotExist() throws XmlParserException {
        //given
        SaxParser saxParser = new SaxParser();
        //when
        List<Flower> result = saxParser.parse(NONEXISTENT_FILE_NAME);
        //then
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldNotParseWhenGivenNull() throws XmlParserException {
        //given
        SaxParser saxParser = new SaxParser();
        //when
        List<Flower> result = saxParser.parse(null);
        //then
        Assert.assertTrue(result.isEmpty());
    }
}
