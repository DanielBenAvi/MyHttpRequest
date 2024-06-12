package com.example.myhttprequests.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class MyResponse {
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public MyResponse() {
    }

    public MyResponse(int responseCode, String responseMessage, String responseBody) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public MyResponse setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public MyResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public MyResponse setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    /**
     * This method is used to get the object from the server response body
     * @apiNote - The object passed in must have the same fields as ALL the response body
     * @param classType - The class type of the
     * @return  - The object returned from the server
     */
    public <T> T getResponseTypedBody(Class<T> classType) {
        return new Gson().fromJson(responseBody, classType);
    }

    @NonNull
    public String toString() {
        return "Response{" +
                "responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
