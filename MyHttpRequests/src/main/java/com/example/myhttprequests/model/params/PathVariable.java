package com.example.myhttprequests.model.params;

import androidx.annotation.NonNull;

public class PathVariable extends Params{
    private String value;

    public PathVariable(String value) {
        super(value);
    }

    @NonNull
    @Override
    public String toString() {
        return "PathVariable{" +
                "value='" + value + '\'' +
                '}';
    }
}
