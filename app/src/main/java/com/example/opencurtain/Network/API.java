package com.example.opencurtain.Network;

import java.net.MalformedURLException;
import java.net.URL;

public enum API {
    users("/user/"),
    subscribes("/subscribes/"),
    boards("/boards/"),
    universitys("/universitys/"),
    facultys("/facultys/"),
    departments("/departments/"),
    posts("/posts/"),
    comments("/comments/"),
    authcode("/authcode/"),
    authcheck("/authcheck/"),
    login("/user/login/"),
    logout("/user/logout/");

    private final String endPoint;
    private static final String HOST = "http://opencurtain.run.goorm.io";
    API(String endPoint) {
        this.endPoint = endPoint;
    }
    public String getEndPoint() {
        return HOST + endPoint;
    }

    public URL appendString(String valueOf) {
        try {
            return new URL(getEndPoint() + valueOf);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
