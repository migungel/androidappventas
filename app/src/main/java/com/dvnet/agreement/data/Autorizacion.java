package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Autorizacion {
    private String id;
    private String name;
    private String company_id;

    public Autorizacion(String id, String name, String company_id) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
    }

    public Autorizacion(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        company_id = cursor.getString(cursor.getColumnIndex("company_id"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("company_id", company_id);
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

    public String getcompany_id() {
        return company_id;
    }

    public void setcompany_id(String company_id) {
        this.name = company_id;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Autorizacion){
            Autorizacion c = (Autorizacion ) obj;
            if(c.getname().equals(name) && c.getId() == id ) return true;
        }
        return false;
    }

}
