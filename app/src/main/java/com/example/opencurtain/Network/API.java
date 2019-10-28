package com.example.opencurtain.Network;

public enum API {
    users("/user"),
    subscribes("/subscribes/"),
    boards("/boards/"),
    universitys("/universitys/"),
    facultys("/facultys/"),
    departments("/departments/"),
    posts("/posts/"),
    comments("/comments/"),
    authcode("/authcode/"),
    authcheck("/authcheck/"),
    login("/user/login/");

    private final String endPoint;
    private static final String HOST = "http://opencurtain-test.run.goorm.io";
    API(String endPoint) {
        this.endPoint = endPoint;
    }
    public String getEndPoint() {
        return HOST + endPoint;
    }
}
