package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class Models {
    private String name;
    private int _id, markId;
    private static String queryCreate="CREATE TABLE models ( _id INTEGER PRIMARY KEY, name TEXT NULL, markId INTEGER NULL, FOREIGN KEY (markId) REFERENCES marks(_id))",
            queryDrop="DROP TABLE IF EXISTS models";

    public static void createTable(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL(queryDrop);
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
