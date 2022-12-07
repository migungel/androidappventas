package com.dvnet.agreement.data;

import android.content.ContentValues;
import android.database.Cursor;

public class User {
    private String id;
    private String user;
    private String pass;
    private String identificacion;
    private String name;
    private String r_desde;
    private String r_hasta;
    private byte[] sing;
    private String active;

    public User(String id,
                String user,
                String pass,
                String identificacion,
                String name,
                String r_desde,
                String r_hasta,
                byte[] sing,
                String active) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.identificacion = identificacion;
        this.name = name;
        this.r_desde = r_desde;
        this.r_hasta = r_hasta;
        this.sing = sing;
        this.active = active;
    }

    public User(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        user = cursor.getString(cursor.getColumnIndex("user"));
        pass = cursor.getString(cursor.getColumnIndex("pass"));
        identificacion = cursor.getString(cursor.getColumnIndex("identificacion"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        r_desde = cursor.getString(cursor.getColumnIndex("r_desde"));
        r_hasta = cursor.getString(cursor.getColumnIndex("r_hasta"));
        sing = cursor.getBlob(cursor.getColumnIndex("sing"));
        active = cursor.getString(cursor.getColumnIndex("active"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("user", user);
        values.put("pass", pass);
        values.put("identificacion", identificacion);
        values.put("name", name);
        values.put("r_desde", r_desde);
        values.put("r_hasta", r_hasta);
        values.put("sing", sing);
        values.put("active", active);
        return values;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIdentificacion() { return this.identificacion; }

    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getR_desde() { return this.r_desde; }

    public void setR_desde(String r_desde) { this.r_desde = r_desde; }

    public String getR_hasta() { return this.r_hasta; }

    public void setR_hasta(String r_hasta) { this.r_hasta = r_hasta; }

    public byte[] getSing() { return this.sing; }

    public void setSing(byte[] sing) { this.sing = sing; }

}
