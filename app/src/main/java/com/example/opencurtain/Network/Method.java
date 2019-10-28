package com.example.opencurtain.Network;

public enum Method {
    GET("GET"),
    POST("POST");

    private final String method;
    Method(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
