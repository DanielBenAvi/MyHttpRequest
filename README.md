# My Ultimate Http Requests

This is a simple http request for android using without any external library for http request.

## Technologies
- Java
- Android
- Gson

## Features
1. Multiple request types
2. Easy to use
3. No external library required
4. Gson for json parsing


## Installation with Gradle

```gradle.kts
dependencies {
    implementation("")
}
```

## Specifications

baseUrl: String - must end without a slash ("/")

| No | Request Type | Parameters                                                                                | Return Type | 
|----|--------------|-------------------------------------------------------------------------------------------|-------------| 
| 1  | GET          | url: String, Callback                                                                     | String      | 
| 2  | GET          | url: String, PathVariables: Array<String> , Callback                                      | String      | 
| 3  | GET          | url: String, QueryParams: Map<String, String>, Callback                                   | String      | 
| 4  | GET          | url: String, PathVariables: Array<String>, QueryParams: Map<String, String>               | String      | 
| 5  | GET          |                                                                                           |             |
|    |              |                                                                                           |             |
| 6  | POST         | url: String, body: String                                                                 | String      | 
| 7  | POST         | url: String, body: String, PathVariables: Array<String>                                   | String      |
| 8  | POST         | url: String, body: String, QueryParams: Map<String, String>                               | String      |
| 9  | POST         | url: String, body: String, PathVariables: Array<String>, QueryParams: Map<String, String> | String      |
| 10 | POST         | url: String, body: String, Callback, Params *                                             | String      | 


## Helper Classes
MyResponse
```java
public class MyResponse {
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    // returns the response as typed object 
    // the custom must be the same as the response json
    public <T> T getResponseTypedBody(Class<T> classType);
}
```

Params
```java
public abstract class Params{}
```

QueryParams
```java
public class QueryParam extends Params  {
    private String key;
    private String value;
}

```

PathVariables
```java
public class PathVariables extends Params {
    private String value;
}
```


## GET Request Examples
1. GET Request - basic
```java
    String url = "path example";
    new HttpRequest().get(url, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

2. GET Request with Path Variables
```java
    String url = "path example";
    Array<String> pathVariables = new Array<String>();
    pathVariables.add("value1");
    new HttpRequest().get(url, pathVariables, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

3. GET Request with Query Parameters
```java
    String url = "path example";
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("key1", "value1");
    new HttpRequest().get(url, queryParams, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

4. GET Request with Path Variables and Query Parameters
```java
    String url = "path example";
    Array<String> pathVariables = new Array<String>();
    pathVariables.add("value1");
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("key1", "value1");
    new HttpRequest().get(url, pathVariables, queryParams, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

5. GET Request with Path Variables and Query Parameters
```java
    String url = "path example";
    new HttpRequest().get(url, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    },
    new QueryParam("key1", "value1"),
    new PathVariables("value1") 
    );
```

## POST Request Examples
1. POST Request - basic
```java
    String url = "path example";
    String body = "body example";
    new HttpRequest().post(url, body, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

2. POST Request with Path Variables
```java
    String url = "path example";
    String body = "body example";
    Array<String> pathVariables = new Array<String>();
    pathVariables.add("value1");
    new HttpRequest().post(url, body, pathVariables, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

3. POST Request with Query Parameters
```java
    String url = "path example";
    String body = "body example";
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("key1", "value1");
    new HttpRequest().post(url, body, queryParams, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```


4. POST Request with Path Variables and Query Parameters
```java
    String url = "path example";
    String body = "body example";
    Array<String> pathVariables = new Array<String>();
    pathVariables.add("value1");
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("key1", "value1");
    new HttpRequest().post(url, body, pathVariables, queryParams, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    });
```

5. POST Request with Path Variables and Query Parameters
```java
    String url = "path example";
    String body = "body example";
    new HttpRequest().post(url, body, new HttpCallback() {
        @Override
        public void onError(String error) {
            Log.e("MainActivity", "Error: " + error);
        }

        @Override
        public void onSuccess(MyResponse response) {
            Log.d("MainActivity", "Response: " + response);
        }
    },
    new QueryParam("key1", "value1"),
    new PathVariables("value1") 
    );
```







## Contributing
Daniel Ben-Avi