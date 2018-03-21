package ru.kirillius.spendcar.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class ParametersCar {
    private int carId, parameterId;
    private float timeLimitReplacement;
    private static String queryCreate="CREATE TABLE parametersCar (carId INTEGER NOT NULL, parameterId INTEGER NOT NULL, " +
            "timeLimitReplacement FLOAT NULL, FOREIGN KEY (carId) REFERENCES cars(_id), " +
            "FOREIGN KEY (parameterId) REFERENCES parameters(_id))",
            queryDrop="DROP TABLE IF EXISTS parametersCar";

    public static void createTable(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL(queryDrop);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public float getTimeLimitReplacement() {
        return timeLimitReplacement;
    }

    public void setTimeLimitReplacement(float timeLimitReplacement) {
        this.timeLimitReplacement = timeLimitReplacement;
    }
}
