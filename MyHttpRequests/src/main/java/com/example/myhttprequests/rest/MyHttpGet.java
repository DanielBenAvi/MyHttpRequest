package com.example.myhttprequests.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class MyHttpGet {
    /**
     * This method is used to make a GET request to the server without any data
     * @param path - The URL to make the request to
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void getRequest(String path, HttpCallback httpCallback) {

        AsyncTask.execute(() -> {
            try {
                URL url = new URL(path);
                HttpURLConnection urlConnection1 = (HttpURLConnection) url.openConnection();
                try {
                    MyResponse response = getResponse(urlConnection1);
                    httpCallback.onSuccess(response);
                } catch (IOException e) {
                    httpCallback.onError(e.getMessage());
                } finally {
                    urlConnection1.disconnect();
                }
            } catch (Exception e) {
                httpCallback.onError(e.getMessage());
            }
        });
    }

    /**
     * This method is used to make a GET request to the server with path variables
     * @param path - The URL to make the request to
     * @param pathVariables - ArrayList of path variables to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void getRequest(String path, ArrayList<String> pathVariables, HttpCallback httpCallback) {
        if (pathVariables == null) {
            Log.d("ERROR", "getRequest: pathVariables is null");
            return;
        }

        if (pathVariables.isEmpty()) {
            Log.d("ERROR", "getRequest: pathVariables is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        for (String pathVariable : pathVariables) {
            newPath.append("/").append(pathVariable);
        }
        getRequest(newPath.toString(), httpCallback);
    }

    /**
     * This method is used to make a GET request to the server with query parameters
     * @param path - The URL to make the request to
     * @param queryParameters - Map of query parameters to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void getRequest(String path, Map<String, String> queryParameters, HttpCallback httpCallback) {
        if (queryParameters == null) {
            Log.d("ERROR", "getRequest: queryParameters is null");
            return;
        }

        if (queryParameters.isEmpty()) {
            Log.d("ERROR", "getRequest: queryParameters is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        newPath.append("?");
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            newPath.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        newPath.deleteCharAt(newPath.length() - 1);
        getRequest(newPath.toString(), httpCallback);
    }

    /**
     * This method is used to make a GET request to the server with path variables and query parameters
     * @param path - The URL to make the request to
     * @param pathVariables - ArrayList of path variables to be added to the URL
     * @param queryParameters - Map of query parameters to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void getRequest(String path, ArrayList<String> pathVariables, Map<String, String> queryParameters, HttpCallback httpCallback) {
        if (pathVariables == null) {
            Log.d("ERROR", "getRequest: pathVariables is null");
            return;
        }

        if (pathVariables.isEmpty()) {
            Log.d("ERROR", "getRequest: pathVariables is empty");
            return;
        }

        StringBuilder newPath = new StringBuilder(path);
        for (String pathVariable : pathVariables) {
            newPath.append("/").append(pathVariable);
        }

        if (queryParameters == null) {
            Log.d("ERROR", "getRequest: queryParameters is null");
            return;
        }

        if (queryParameters.isEmpty()) {
            Log.d("ERROR", "getRequest: queryParameters is empty");
            return;
        }

        newPath.append("?");
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            newPath.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        newPath.deleteCharAt(newPath.length() - 1);
        getRequest(newPath.toString(), httpCallback);
    }


    private MyResponse getResponse(HttpURLConnection httpURLConnection) throws IOException {
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

}
