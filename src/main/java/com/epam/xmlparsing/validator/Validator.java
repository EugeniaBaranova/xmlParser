package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.validator.exeption.ValidationException;

public interface Validator {

    ValidationResult validate(String fileName) throws ValidationException;

}
