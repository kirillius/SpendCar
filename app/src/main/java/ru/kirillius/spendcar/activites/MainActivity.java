package ru.kirillius.spendcar.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.database.models.Cars;
import ru.kirillius.spendcar.database.orm.CarsController;
import ru.kirillius.spendcar.services.CommonHelper;

public class MainActivity extends AppCompatActivity {

    Context context;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        context = MainActivity.this;
        gson = new Gson();

        long count = new CarsController(context).getCount();
        if(count<=0) {
            Intent intent = new Intent(this, AddCarActivity.class);
            intent.putExtra("count", count);
            startActivity(intent);
            finish();
        }

        Cars myCar = new CarsController(context).findById(1);
        System.out.println("car: "+gson.toJson(myCar));

        CommonHelper.setStatusBarColor(this);
        //startService(new Intent(this, InitAllData.class));
    }
}
