package com.example.opencurtain.Network;

import org.json.JSONObject;

public interface RequestHandler {
    void onRequestOK(JSONObject jsonObject);
    void onRequestErr(int code);
}
