package ru.kirillius.spendcar.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.services.CommonHelper;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CommonHelper.setStatusBarColor(this);

        //Название машины, фоточка (по дефолту будет тоже), год выпуска, марка, модель, показания одометра
        //заявленый расход бенза
        //какие показатели по дефолту учитывать (стоять будут галки на бензине, масле и фильтрах всех)
    }
}
