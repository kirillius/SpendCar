package ru.kirillius.spendcar.database.initData;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.orm.BasicFunctionDB;
import ru.kirillius.spendcar.database.models.Parameters;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class ParametersInit {
    private ArrayList<Parameters> initData = new ArrayList<Parameters>(){{
        add(new Parameters("Топливо", 0, -1));
        add(new Parameters("Масло ДВС", 1, 6000));
        add(new Parameters("Тормозная жидкость", 1, 10000));
        add(new Parameters("Воздушный фильтр", 1, 5000));
        add(new Parameters("Салонный фильтр", 1, 5000));
        add(new Parameters("Маслянный фильтр", 1, 6000));
        add(new Parameters("Топливный фильтр", 1, 5000));
        add(new Parameters("Антифриз", 1, 10000));
    }};
    public ParametersInit(Context context) {
        BasicFunctionDB bfDB = new BasicFunctionDB(context);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();

        for(Parameters item: initData) {
            String jsonItem = gson.toJson(item);
            bfDB.insert(item.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonItem, map.getClass()));
        }
    }
}
