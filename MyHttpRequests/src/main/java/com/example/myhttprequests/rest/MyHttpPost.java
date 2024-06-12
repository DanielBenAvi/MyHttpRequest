package com.example.myhttprequests.rest;

import static com.example.myhttprequests.rest.RestHelper.getResponse;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class MyHttpPost {

    /**
     * This method is used to make a POST request to the server with data as a JSON string
     * @param path - The URL to make the request to
     * @param data - The data to be sent to the server
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void postRequest(String path, String data, HttpCallback httpCallback) {
        AsyncTask.execute(() -> {
            try {
                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setDoOutput(true);

                try(OutputStream os = urlConnection.getOutputStream()) {
                    byte[] input = data.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                MyResponse response = getResponse(urlConnection);
                httpCallback.onSuccess(response);
            } catch (Exception e) {
                httpCallback.onError(e.getMessage());
            }

        });
    }

    /**
     * This method is used to make a POST request to the server with data as a JSON string
     * @param path - The URL to make the request to
     * @param data - The data to be sent to the server
     * @param pathVariables - ArrayList of path variables to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void postRequest(String path, String data, ArrayList<String> pathVariables, HttpCallback httpCallback) {
        if (pathVariables == null) {
            Log.d("ERROR", "postRequest: pathVariables is null");
            return;
        }

        if (pathVariables.isEmpty()) {
            Log.d("ERROR", "postRequest: pathVariables is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        for (String pathVariable : pathVariables) {
            newPath.append("/").append(pathVariable);
        }

        postRequest(newPath.toString(), data, httpCallback);
    }


    /**
     * This method is used to make a POST request to the server with data as a JSON string
     * @param path - The URL to make the request to
     * @param data - The data to be sent to the server
     * @param queryParams - The query parameters to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void postRequest(String path, String data, HashMap<String, String> queryParams, HttpCallback httpCallback) {
        if (queryParams == null) {
            Log.d("ERROR", "postRequest: queryParams is null");
            return;
        }

        if (queryParams.isEmpty()) {
            Log.d("ERROR", "postRequest: queryParams is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        newPath.append("?");

        for (String key : queryParams.keySet()) {
            newPath.append(key).append("=").append(queryParams.get(key)).append("&");
        }

        postRequest(newPath.toString(), data, httpCallback);
    }


    /**
     * This method is used to make a POST request to the server with data as a JSON string
     * @param path - The URL to make the request to
     * @param data - The data to be sent to the server
     * @param pathVariables - ArrayList of path variables to be added to the URL
     * @param queryParams - The query parameters to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void postRequest(String path, String data, ArrayList<String> pathVariables, HashMap<String, String> queryParams, HttpCallback httpCallback) {
        if (pathVariables == null) {
            Log.d("ERROR", "postRequest: pathVariables is null");
            return;
        }

        if (pathVariables.isEmpty()) {
            Log.d("ERROR", "postRequest: pathVariables is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        for (String pathVariable : pathVariables) {
            newPath.append("/").append(pathVariable);
        }

        if (queryParams == null) {
            Log.d("ERROR", "postRequest: queryParams is null");
            return;
        }

        if (queryParams.isEmpty()) {
            Log.d("ERROR", "postRequest: queryParams is empty");
            return;
        }

        newPath.append("?");
        for (String key : queryParams.keySet()) {
            newPath.append(key).append("=").append(queryParams.get(key)).append("&");
        }

        postRequest(newPath.toString(), data, httpCallback);
    }

}
