package com.example.myhttprequests.model.params;

import androidx.annotation.NonNull;

public abstract class Params {
    protected String value;

    public Params(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @NonNull
    public String toString() {
        return "Params{" +
                "value='" + value + '\'' +
                "}";
    }
}
