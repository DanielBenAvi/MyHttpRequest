package com.example.myhttprequests.rest;

import static com.example.myhttprequests.rest.RestHelper.getResponse;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;
import com.example.myhttprequests.model.params.Params;
import com.example.myhttprequests.model.params.PathVariable;
import com.example.myhttprequests.model.params.QueryParam;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        Log.d("INFO", "getRequest: newPath: " + newPath.toString());

        getRequest(newPath.toString(), httpCallback);
    }

    /**
     * This method is used to make a GET request to the server with query parameters
     * @param path - The URL to make the request to
     * @param queryParameters - Map of query parameters to be added to the URL
     * @param httpCallback - The callback to be called when the request is complete
     */
    public void getRequest(String path, Map<String, String> queryParameters, HttpCallback httpCallback) {
        Log.d("INFO", "getRequest: queryParameters: " + queryParameters);
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

        Log.d("INFO", "getRequest: newPath: " + newPath);

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


    /**
     * This method is used to make a GET request to the server with path variables and query parameters
     * @param path - The URL to make the request to
     * @param httpCallback - The callback to be called when the request is complete
     * @param Params * - List of path variables and query parameters to be added to the URL
     */
    public void getRequest(String path, HttpCallback httpCallback, Params... Params) {
        if (Params.length == 0) {
            getRequest(path, httpCallback);
            Log.d("ERROR", "getRequest: Params is empty");
            return;
        }

        for (Params param : Params) {
            Log.d("INFO", param.toString());

        }

        ArrayList<String> pathVariables = new ArrayList<>();
        HashMap<String, String> queryParams = new HashMap<>();

        for (Params param : Params) {
            if (param.getClass() == PathVariable.class) {
                PathVariable pathVariable = (PathVariable) param;
                pathVariables.add(pathVariable.getValue());
            }

            if (param.getClass() == QueryParam.class) {
                QueryParam queryParam = (QueryParam) param;
                queryParams.put(queryParam.getKey(), queryParam.getValue());
            }
        }


        if (pathVariables.isEmpty() && queryParams.isEmpty()) {
            getRequest(path, httpCallback);
            Log.d("INFO", "getRequest: pathVariables and queryParams are empty");
        } else if (pathVariables.isEmpty()) {
            getRequest(path, queryParams, httpCallback);
            Log.d("INFO", "getRequest: pathVariables is empty");
            Log.d("INFO", "getRequest: queryParams is not empty");
        } else if (queryParams.isEmpty()) {
            getRequest(path, pathVariables, httpCallback);
            Log.d("INFO", "getRequest: queryParams is empty");
            Log.d("INFO", "getRequest: pathVariables is not empty");
        } else {
            getRequest(path, pathVariables, queryParams, httpCallback);
            Log.d("INFO", "getRequest: pathVariables and queryParams are not empty");
        }
    }

}
