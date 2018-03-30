package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Lavrentev on 21.03.2018.
 * Класс для реализации в БД сущности "Машина"
 */

public class Cars {
    private String name, pathToPhoto, tableName = "cars";
    private int yearCreate, markId, modelId, _id, mainCar;
    private float odometr, eatFuel;
    private ArrayList<Parameters> parametersCar;
    private Marks markCar;
    private Models modelCar;
    private static String queryCreate="CREATE TABLE cars ( _id INTEGER PRIMARY KEY, name TEXT NULL, " +
            "markId INTEGER NULL, modelId INTEGER NULL, yearCreate INTEGER NULL, odometr FLOAT NULL, " +
            "eatFuel FLOAT NULL, pathToPhoto VARCHAR(800) NULL, mainCar INTEGER DEFAULT 0, FOREIGN KEY (markId) REFERENCES marks(_id), FOREIGN KEY (modelId) REFERENCES models(_id))",
            queryDrop="DROP TABLE IF EXISTS cars";


    public static void createTable(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL(queryDrop);
    }

    public String getTableName() {
        return tableName;
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

    public ArrayList<Parameters> getParametersCar() {
        return parametersCar;
    }

    public void setParametersCar(ArrayList<Parameters> parametersCar) {
        this.parametersCar = parametersCar;
    }

    public Marks getMarkCar() {
        return markCar;
    }

    public void setMarkCar(Marks markCar) {
        this.markCar = markCar;
    }

    public Models getModelCar() {
        return modelCar;
    }

    public void setModelCar(Models modelCar) {
        this.modelCar = modelCar;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    public int getMainCar() {
        return mainCar;
    }

    public void setMainCar(int mainCar) {
        this.mainCar = mainCar;
    }
}
