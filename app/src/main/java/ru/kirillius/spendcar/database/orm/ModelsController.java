package ru.kirillius.spendcar.database.orm;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.models.Models;

/**
 * Created by Lavrentev on 27.03.2018.
 */

public class ModelsController {
    Gson gson;
    BasicFunctionDB bfDB;
    Context context;

    public ModelsController(Context context) {
        this.context = context;
        gson = new Gson();
        bfDB = new BasicFunctionDB(context);
    }

    public ArrayList<Models> get() {
        ArrayList<HashMap<String, String>> list = bfDB.get("models");
        ArrayList<Models> result = new ArrayList<>();
        for (HashMap<String, String> item: list) {
            Models model = gson.fromJson(gson.toJson(item), Models.class);
            result.add(model);
        }

        return result;
    }

    public Models findById(int _id) {
        Models model = null;
        HashMap<String, String> modelMap = bfDB.get("models", "_id = ?", new String[] {String.valueOf(_id)});
        if(modelMap!=null)
            model = gson.fromJson(gson.toJson(modelMap), Models.class);

        return model;
    }

    public Models find(String[] fields, String[] values) {
        ArrayList<String> fieldsList = new ArrayList<>();
        for(String item: fields) {
            fieldsList.add(item+" = ?");
        }

        Models model = null;
        HashMap<String, String> modelMap = bfDB.get("models", TextUtils.join(",", fieldsList), values);
        if(modelMap!=null)
            model = gson.fromJson(gson.toJson(modelMap), Models.class);

        return model;
    }

    public long create(Models model) {
        Map<String,Object> map = new HashMap<String,Object>();
        String jsonCar = gson.toJson(model);
        return bfDB.insert(model.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonCar, map.getClass()));
    }

    public void update() {

    }

    public void delete() {

    }
}
