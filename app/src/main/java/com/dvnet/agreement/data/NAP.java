package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class NAP {

    private String id;
    private String name;
    private String canton_id;

    public NAP(String id, String name, String canton_id) {
        this.id = id;
        this.name = name;
        this.canton_id = canton_id;
    }

    public NAP(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        canton_id = cursor.getString(cursor.getColumnIndex("canton_id"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("canton_id", canton_id);
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

    public String getCanton_id() { return canton_id; }

    public void setCanton_id(String canton_id) { this.canton_id = canton_id; }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NAP){
            NAP c = (NAP )obj;
            if(c.getname().equals(name) && c.getId() == id ) return true;
        }
        return false;
    }

}
