package com.example.myhttprequests.model.params;

public class QueryParam extends Params {
    private String key;

    public QueryParam(String key, String value) {
        super(value);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
