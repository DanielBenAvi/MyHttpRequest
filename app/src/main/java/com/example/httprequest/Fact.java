package com.example.httprequest;

import java.util.ArrayList;

public class Fact {
    private ArrayList<String> facts;
    private Boolean success;

    public Fact(){}

    public ArrayList<String> getFacts() {
        return facts;
    }

    public Fact setFacts(ArrayList<String> facts) {
        this.facts = facts;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Fact setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
