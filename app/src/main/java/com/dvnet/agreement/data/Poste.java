package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.dvnet.agreement.data.partner.PartnerContract;

public class Poste {
    private String id;
    private String name;
    private String sector_id;

    public Poste(String id, String name, String sector_id) {
        this.id = id;
        this.name = name;
        this.sector_id = sector_id;
    }

    public Poste(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        sector_id = cursor.getString(cursor.getColumnIndex("sector_id"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("sector_id", sector_id);
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

    public String getSector_id() { return sector_id; }

    public void setSector_id(String sector_id) { this.sector_id = sector_id; }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Poste){
            Poste c = (Poste )obj;
            if(c.getname().equals(name) && c.getId() == id ) return true;
        }
        return false;
    }
}
