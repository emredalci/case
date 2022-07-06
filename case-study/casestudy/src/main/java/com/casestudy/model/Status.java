package com.casestudy.model;

public enum Status {

    ACCEPT("accepted",0),
    REJECT("rejected",1);

    private final String name;
    private final int code;

    Status(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
