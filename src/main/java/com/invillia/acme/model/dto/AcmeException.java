package com.invillia.acme.model.dto;

import java.io.Serializable;

public class AcmeException implements Serializable {

    private String message;
    private String exceptionType;

    public String getExceptionType() {
        return exceptionType;
    }

    public AcmeException setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AcmeException setMessage(String message) {
        this.message = message;
        return this;
    }
}
