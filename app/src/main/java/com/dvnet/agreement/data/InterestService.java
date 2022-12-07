package com.dvnet.agreement.data;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.UUID;

public class InterestService {
    private String id;
    private String refer;
    private String services;
    private String actual_service;

    public InterestService(){}

    public InterestService(String id, String refer, String services, String actual_service) {
        if (id != "0") {
            this.id = id;
        } else {
            this.id = UUID.randomUUID().toString();
        }

        this.refer = refer;
        this.services = services;
        this.actual_service = actual_service;
    }

    public InterestService(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        refer = cursor.getString(cursor.getColumnIndex("refer"));
        services = cursor.getString(cursor.getColumnIndex("services"));
        actual_service = cursor.getString(cursor.getColumnIndex("actual_service"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("refer", refer);
        values.put("services", services);
        values.put("actual_service", actual_service);
        return values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getActualService() {
        return actual_service;
    }

    public void setActualService(String actual_service) {
        this.actual_service = actual_service;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Smart){
            Smart c = (Smart )obj;
            if(c.getId()==id ) return true;
        }

        return false;
    }
}

