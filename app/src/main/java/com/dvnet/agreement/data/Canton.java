package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Canton {
    private String id;
    private String name;
    private String provincia_id;

    public Canton(String id, String name, String provincia_id) {
        this.id = id;
        this.name = name;
        this.provincia_id = provincia_id;
    }

    public Canton(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        provincia_id = cursor.getString(cursor.getColumnIndex("provincia_id"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("provincia_id", provincia_id);
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

    public String getProvincia_id() { return provincia_id; }

    public void setProvincia_id(String provincia_id) { this.provincia_id = provincia_id; }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Canton){
            Canton c = (Canton )obj;
            if(c.getname().equals(name) && c.getId() == id ) return true;
        }
        return false;
    }
}
