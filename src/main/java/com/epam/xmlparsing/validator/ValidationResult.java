package com.epam.xmlparsing.validator;

import java.util.Objects;

public class ValidationResult {

    private Boolean validity;

    private String errorMessage;

    public Boolean getValidity() {
        return validity;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

     @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ValidationResult guest = (ValidationResult) object;

        return Objects.equals(this.getValidity(), guest.getValidity()) &&
                Objects.equals(this.getErrorMessage(), guest.getErrorMessage());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        if (this.getValidity() != null) {
            Boolean validity = this.getValidity();
            result = prime * result + validity.hashCode();
        }
        if (this.getErrorMessage() != null) {
            String errorMessage = this.getErrorMessage();
            result = prime * result + errorMessage.hashCode();
        }
        return result;
    }

}
