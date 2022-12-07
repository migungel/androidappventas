package com.dvnet.agreement.data.partner;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

public class Management {

    private String id;
    private String refer;
    private String motive;
    private String management;
    private String user_id;
    private String state;
    private String date;

    public Management(){}

    public Management(
            String id,
            String refer,
            String motive,
            String management,
            String user_id,
            String state,
            String date
    ){
        if (id != "0") {
            this.id = id;
        }
        else {
            this.id = UUID.randomUUID().toString();
        }
        this.refer = refer;
        this.motive = motive;
        this.management = management;
        this.user_id = user_id;
        this.state = state;
        this.date = date;
    }

    public Management(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex("id"));
        refer = cursor.getString(cursor.getColumnIndex("refer"));
        motive = cursor.getString(cursor.getColumnIndex("motive"));
        management = cursor.getString(cursor.getColumnIndex("management"));
        user_id = cursor.getString(cursor.getColumnIndex("user_id"));
        state = cursor.getString(cursor.getColumnIndex("state"));
        date = cursor.getString(cursor.getColumnIndex("date"));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("refer", refer);
        values.put("motive", motive);
        values.put("management", management);
        values.put("user_id", user_id);
        values.put("state", state);
        values.put("date", date);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) { this.refer = refer; }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) { this.motive = motive; }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) { this.management = management; }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getState() {
        return state;
    }

    public void setState(String state) { this.state = state; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }
}
