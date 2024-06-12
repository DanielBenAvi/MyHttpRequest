package com.example.myhttprequests;

import android.util.Log;

import java.lang.reflect.Constructor;

public class Helper {

    public <T> T createObject(Class<T> classType) {
        T object = null;
        try {
            Constructor<T> constructor = classType.getConstructor();
            object = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
