package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.dvnet.agreement.data.partner.PartnerContract;

public class Smart {
    private String id;
    private String name;
    private String code;
    private String active;

    public Smart(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Smart(String id, String name, String code, String active){
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
    }

    public Smart(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        code = cursor.getString(cursor.getColumnIndex("code"));
        active = cursor.getString(cursor.getColumnIndex("active"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("code", code);
        values.put("active", active);
        return values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }


    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Smart){
            Smart c = (Smart )obj;
            if(c.getName().equals(name) && c.getId()==id ) return true;
        }

        return false;
    }

}
