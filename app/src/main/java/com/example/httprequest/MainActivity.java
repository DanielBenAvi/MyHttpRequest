package com.example.httprequest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;
import com.example.myhttprequests.rest.MyHttpPost;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        button.setOnClickListener(v -> loadDogFact());
    }

    private void findViews() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
    }

    private void loadDogFact() {
//        MyHttpGet httpGet = new MyHttpGet();
//        String baseUrl = "http://10.0.2.2:5000/parameters";
//        ArrayList<String> pathVariables = new ArrayList<>();
//        pathVariables.add("dog");
//
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("password", "1234");
//
//        httpGet.getRequest(baseUrl, queryParams , new HttpCallback() {
//
//            @Override
//            public void onError(String error) {
//                textView.setText(error);
//            }
//
//            @Override
//            public void onSuccess(MyResponse response) {
//                textView.setText(response.getResponseBody());
//            }
//        });

        MyHttpPost httpPost = new MyHttpPost();
        String baseUrl = "http://10.0.2.2:5000/post";
        String data = "{\"username\":\"daniel\",\"password\":\"1234\"}";
        httpPost.postRequest(baseUrl, data, new HttpCallback() {
            @Override
            public void onError(String error) {
                textView.setText(error);
            }

            @Override
            public void onSuccess(MyResponse response) {
                textView.setText(response.getResponseBody());
            }
        });


    }








}

