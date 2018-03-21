package ru.kirillius.spendcar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.kirillius.spendcar.database.models.Cars;
import ru.kirillius.spendcar.database.models.Marks;
import ru.kirillius.spendcar.database.models.Models;
import ru.kirillius.spendcar.database.models.Parameters;
import ru.kirillius.spendcar.database.models.ParametersCar;

/**
 * Created by Lavrentev on 02.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "spendCarDB.db";
    private static final int DB_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Parameters.createTable(db);
        Marks.createTable(db);
        Models.createTable(db);
        Cars.createTable(db);
        ParametersCar.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Parameters.dropTable(db);
        Marks.dropTable(db);
        Models.dropTable(db);
        Cars.dropTable(db);
        ParametersCar.dropTable(db);

        Parameters.createTable(db);
        Marks.createTable(db);
        Models.createTable(db);
        Cars.createTable(db);
        ParametersCar.createTable(db);
    }
}
