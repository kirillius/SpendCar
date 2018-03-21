package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class Parameters {
    private String name, tableName = "parameters";
    private int _id, seasonalReplacement;
    private float timeLimitReplacement;
    private static String queryCreate="CREATE TABLE parameters ( _id INTEGER PRIMARY KEY, name TEXT NULL, seasonalReplacement INTEGER NULL DEFAULT 0, timeLimitReplacement FLOAT NULL)",
            queryDrop="DROP TABLE IF EXISTS parameters";

    public Parameters() {

    }

    public Parameters(String name, int seasonalReplacement, float timeLimitReplacement) {
        this.name = name;
        this.seasonalReplacement = seasonalReplacement;
        this.timeLimitReplacement = timeLimitReplacement;
    }

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

    public int getSeasonalReplacement() {
        return seasonalReplacement;
    }

    public void setSeasonalReplacement(int seasonalReplacement) {
        this.seasonalReplacement = seasonalReplacement;
    }

    public float getTimeLimitReplacement() {
        return timeLimitReplacement;
    }

    public void setTimeLimitReplacement(float timeLimitReplacement) {
        this.timeLimitReplacement = timeLimitReplacement;
    }
}
