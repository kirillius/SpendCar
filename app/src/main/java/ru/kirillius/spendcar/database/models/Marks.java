package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class Marks {
    private String name, tableName = "marks";
    private int _id;
    private static String queryCreate="CREATE TABLE marks ( _id INTEGER PRIMARY KEY, name TEXT NULL)",
            queryDrop="DROP TABLE IF EXISTS marks";

    public static void createTable(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL(queryDrop);
    }

    public String getTableName() {
        return tableName;
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
