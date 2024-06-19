package com.example.myhttprequests.rest;

import com.example.myhttprequests.model.MyResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class RestHelper {
    /**
     * This method is used to get the response from the server
     * @param httpURLConnection - The connection to the server
     * @return MyResponse - The response from the server as a MyResponse object
     * @throws IOException - If there is an error reading the response
     */
    public static MyResponse getResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        int statusCode = httpURLConnection.getResponseCode();
        String statusMessage = httpURLConnection.getResponseMessage();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        return new MyResponse(statusCode, statusMessage, result.toString());
    }

    public static String mapToBody(Map<String, String> data) {
        StringBuilder body = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            body.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        body.deleteCharAt(body.length() - 1);
        body.append("}");
        return body.toString();
    }

}
