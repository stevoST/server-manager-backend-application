package com.chookity.servermanagerbackendapplication.enumeration;

public enum Status {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    Status(final String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
