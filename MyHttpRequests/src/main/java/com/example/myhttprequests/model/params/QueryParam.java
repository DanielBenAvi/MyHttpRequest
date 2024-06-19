package com.example.myhttprequests.model.params;

import androidx.annotation.NonNull;

public class QueryParam extends Params {
    private String key;

    public QueryParam(String key, String value) {
        super(value);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @NonNull
    @Override
    public String toString() {
        return "QueryParam{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
