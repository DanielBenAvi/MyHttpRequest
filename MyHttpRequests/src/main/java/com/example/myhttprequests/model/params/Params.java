package com.example.myhttprequests.model.params;

public abstract class Params {
    protected String value;

    public Params(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
