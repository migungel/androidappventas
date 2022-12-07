package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Tecnologia {
    private String id;
    private String name;
    private String type_service;
    private String active;

    public Tecnologia(String id, String name, String type_service, String active) {
        this.id = id;
        this.name = name;
        this.type_service = type_service;
        this.active = active;
    }

    public Tecnologia(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        type_service = cursor.getString(cursor.getColumnIndex("type_service"));
        active = cursor.getString(cursor.getColumnIndex("active"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("type_service", type_service);
        values.put("active", active);
        return values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getType_service() { return type_service; }

    public void setType_service(String type_service) { this.type_service = type_service; }

    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tecnologia){
            Tecnologia c = (Tecnologia )obj;
            if(c.getname().equals(name) && c.getId()==id ) return true;
        }

        return false;
    }

}
