package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lavrentev on 21.03.2018.
 * Класс для реализации в БД сущности "Машина"
 */

public class Cars {
    private String name;
    private int yearCreate, markId, modelId, _id;
    private float odometr, eatFuel;
    private static String queryCreate="CREATE TABLE cars ( _id INTEGER PRIMARY KEY, name TEXT NULL, " +
            "markId INTEGER NULL, modelId INTEGER NULL, yearCreate INTEGER NULL, odometr FLOAT NULL, " +
            "eatFuel FLOAT NULL, FOREIGN KEY (markId) REFERENCES marks(_id), FOREIGN KEY (modelId) REFERENCES models(_id))",
            queryDrop="DROP TABLE IF EXISTS cars";


    public static void createTable(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL(queryDrop);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearCreate() {
        return yearCreate;
    }

    public void setYearCreate(int yearCreate) {
        this.yearCreate = yearCreate;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public float getOdometr() {
        return odometr;
    }

    public void setOdometr(float odometr) {
        this.odometr = odometr;
    }

    public float getEatFuel() {
        return eatFuel;
    }

    public void setEatFuel(float eatFuel) {
        this.eatFuel = eatFuel;
    }
}
