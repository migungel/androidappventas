package com.dvnet.agreement.data;

public class GlobalUser {
    String userId;

    private static final GlobalUser ourInstance = new GlobalUser();

    public static GlobalUser getInstance() {
        return ourInstance;
    }

    private GlobalUser() {
    }

    public void setData(String userid) {
        this.userId = userid;
    }

    public String getData() {
        return userId;
    }

}
