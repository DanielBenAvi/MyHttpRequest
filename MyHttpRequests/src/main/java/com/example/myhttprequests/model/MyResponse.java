package com.example.myhttprequests.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

/**
 * This class is used to create a response object from the server
 * @implSpec - The response object is used to store the response code, response message and response body from the server
 * @implNote - The response body can be converted to an object using the getResponseTypedBody method
 */
public class MyResponse {
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public MyResponse() {
    }

    /**
     * This constructor is used to create a response object
     * @param responseCode - The response code from the server
     * @param responseMessage - The response message from the server
     * @param responseBody - The response body from the server
     */
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
