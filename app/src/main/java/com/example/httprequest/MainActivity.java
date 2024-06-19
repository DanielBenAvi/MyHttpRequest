package com.example.httprequest;

import static com.example.myhttprequests.rest.RestHelper.mapToBody;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;
import com.example.myhttprequests.model.params.PathVariable;
import com.example.myhttprequests.model.params.QueryParam;
import com.example.myhttprequests.rest.MyHttpPost;
import com.google.android.material.button.MaterialButton;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MaterialButton button;
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
        MyHttpPost httpPost = new MyHttpPost();
        String baseUrl = "http://10.0.2.2:5000/parameters";
        Map<String, String> data = Map.of("username", "daniel", "password", "1234");

        httpPost.postRequest(baseUrl, mapToBody(data), new HttpCallback() {
            @Override
            public void onError(String error) {
                textView.setText(error);
            }

            @Override
            public void onSuccess(MyResponse response) {
                textView.setText(response.getResponseBody());
            }
        },
            new QueryParam("password", "1234"),
            new QueryParam("username", "daniel")
        );


    }








}

