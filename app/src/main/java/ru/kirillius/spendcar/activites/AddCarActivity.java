package ru.kirillius.spendcar.activites;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.database.BasicFunctionDB;
import ru.kirillius.spendcar.database.models.Parameters;
import ru.kirillius.spendcar.services.CommonHelper;

public class AddCarActivity extends AppCompatActivity {

    Context context;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CommonHelper.setStatusBarColor(this);

        context = AddCarActivity.this;

        //Название машины, фоточка (по дефолту будет тоже), год выпуска, марка, модель, показания одометра
        //заявленый расход бенза
        //какие показатели по дефолту учитывать (стоять будут галки на бензине, масле и фильтрах всех)

        BasicFunctionDB bfDB = new BasicFunctionDB(context);
        ArrayList<HashMap<String, String>> list = bfDB.get("parameters");
        for (HashMap<String, String> item: list) {
            Parameters parameter = gson.fromJson(gson.toJson(item), Parameters.class);

        }
    }
}
