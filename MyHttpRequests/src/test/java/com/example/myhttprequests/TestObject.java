package com.example.myhttprequests;

public class TestObject {
    private String name;
    private int age;

    private TestObject() {
    }

    public TestObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
