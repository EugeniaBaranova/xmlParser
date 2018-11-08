package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.validator.exeption.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;


public class XmlValidatorTest {

    private static final String VALID_FILE = "input.xml";
    private static final String INVALID_FILE = "invalid.xml";
    private static final String NONEXISTENT_FILE_NAME = "xnjksdkv";

    private static final String NONEXISTENT_SCHEMA = "nonexistentSchema.xml";
    private static final String XSD_SCHEMA = "schema.xml";

    private XmlValidator xmlValidator = new XmlValidator(XSD_SCHEMA);

    @Test
    public void shouldValidateEndReturnTrueWithoutMessageWhenGivenValidFile() throws ValidationException {
        //when
        ValidationResult result = xmlValidator.validate(VALID_FILE);
        //then
        Assert.assertTrue(result.getValidity());
        Assert.assertThat(result.getErrorMessage(), is(nullValue()));
    }

    @Test
    public void shouldValidateEndReturnFalseWithMessageWhenGivenInvalidFile() throws ValidationException {
        //when
        ValidationResult result = xmlValidator.validate(INVALID_FILE);
        //then
        Assert.assertFalse(result.getValidity());
        Assert.assertThat(result.getErrorMessage(),
                is(ErrorMessages.INVALID_FILE));
    }

    @Test
    public void shouldValidateAndReturnMessageWithoutValidityWhenGivenNull() throws ValidationException {
        //when
        ValidationResult result = xmlValidator.validate(null);
        //then
        Assert.assertThat(result.getValidity(), is(nullValue()));
        Assert.assertThat(result.getErrorMessage(),
                is(ErrorMessages.NULL_FILE_NAME));
    }

    @Test
    public void shouldValidateAndReturnMessageWithoutValidityWhenFileNotExist() throws ValidationException {
        //when
        ValidationResult result = xmlValidator.validate(NONEXISTENT_FILE_NAME);
        //then
        Assert.assertThat(result.getValidity(), is(nullValue()));
        Assert.assertThat(result.getErrorMessage(),
                is(ErrorMessages.NONEXISTENT_FILE));
    }

    @Test
    public void shouldValidateAndReturnMessageWithoutValidityWhenSchemaNotExist() throws ValidationException {
        //given
        XmlValidator xmlValidator = new XmlValidator(NONEXISTENT_SCHEMA);
        //when
        ValidationResult result = xmlValidator.validate(VALID_FILE);
        //then
        Assert.assertThat(result.getValidity(), is(nullValue()));
        Assert.assertThat(result.getErrorMessage(),
                is(ErrorMessages.NONEXISTENT_SCHEMA));
    }
}
