package com.dvnet.agreement.data;


import android.content.ContentValues;
import android.database.Cursor;

public class ActualService {
    private String id;
    private String service;
    private String provider;

    public ActualService(String service, String provider) {
        this.service = service;
        this.provider = provider;
    }

    public ActualService(String id, String service, String provider) {
        this.id = id;
        this.service = service;
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getProvider() {
        return provider;
    }

//    to display object as a string in spinner
    @Override
    public String toString() {
        return service + " - " + provider;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ActualService){
            ActualService c = (ActualService )obj;
            if(c.getService().equals(service) && c.getProvider().equals(provider) ) return true;
        }
        return false;
    }
}

