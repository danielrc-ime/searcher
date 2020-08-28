package com.test.liverpoolsearcher.Model;

import org.json.JSONObject;

public interface RequestAuthListener {
    void onSuccesRequest(JSONObject jsonObject);
    void onFailureRequest(Exception e);
}
