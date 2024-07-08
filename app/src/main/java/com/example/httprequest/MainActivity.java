package com.example.httprequest;

import static com.example.myhttprequests.rest.RestHelper.mapToBody;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;
import com.example.myhttprequests.model.params.PathVariable;
import com.example.myhttprequests.model.params.QueryParam;
import com.example.myhttprequests.rest.MyHttpGet;
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
        String baseUrl = "https://dogapi.dog/api/facts";
        MyHttpPost myHttpPost = new MyHttpPost();
        MyHttpGet myHttpGet = new MyHttpGet();

        myHttpGet.getRequest(baseUrl, new HttpCallback() {

            @Override
            public void onError(String error) {
                Log.e("MainActivity", "Error: " + error);
            }

            @Override
            public void onSuccess(MyResponse response) {
                Log.d("MainActivity", "Response: " + response);
                textView.setText(response.getResponseTypedBody(Fact.class).getFacts().get(0));

            }
        });




    }








}

