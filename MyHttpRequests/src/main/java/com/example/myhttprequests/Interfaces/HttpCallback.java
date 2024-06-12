package com.example.myhttprequests.Interfaces;

import com.example.myhttprequests.model.MyResponse;

public interface HttpCallback {
        void onError(String error);

        void onSuccess(MyResponse response);
}
