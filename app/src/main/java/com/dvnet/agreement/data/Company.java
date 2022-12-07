package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Company {
    private String id;
    private String name;
    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
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

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Company){
            Company c = (Company )obj;
            if(c.getname().equals(name) && c.getId() == id ) return true;
        }
        return false;
    }

}
