package com.borja.cms.domain.auth.model;

public enum Reason {
    ALREADY_EXISTENT_USERNAME("CMS-AUTH-01", "The username already exists");
    private final String code;
    private final String message;

    Reason(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
