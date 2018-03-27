package ru.kirillius.spendcar.database.orm;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.models.Marks;

/**
 * Created by Lavrentev on 27.03.2018.
 */

public class MarksController {
    Gson gson;
    BasicFunctionDB bfDB;
    Context context;

    public MarksController(Context context) {
        this.context = context;
        gson = new Gson();
        bfDB = new BasicFunctionDB(context);
    }

    public ArrayList<Marks> get() {
        ArrayList<HashMap<String, String>> list = bfDB.get("marks");
        ArrayList<Marks> result = new ArrayList<>();
        for (HashMap<String, String> item: list) {
            Marks mark = gson.fromJson(gson.toJson(item), Marks.class);
            result.add(mark);
        }

        return result;
    }

    public Marks findById(int _id) {
        Marks mark = null;
        HashMap<String, String> markMap = bfDB.get("marks", "_id = ?", new String[] {String.valueOf(_id)});
        if(markMap!=null)
            mark = gson.fromJson(gson.toJson(markMap), Marks.class);

        return mark;
    }

    public Marks find(String[] fields, String[] values) {
        ArrayList<String> fieldsList = new ArrayList<>();
        for(String item: fields) {
            fieldsList.add(item+" = ?");
        }

        Marks mark = null;
        HashMap<String, String> markMap = bfDB.get("marks", TextUtils.join(",", fieldsList), values);
        if(markMap!=null)
            mark = gson.fromJson(gson.toJson(markMap), Marks.class);

        return mark;
    }

    public long create(Marks model) {
        Map<String,Object> map = new HashMap<String,Object>();
        String jsonCar = gson.toJson(model);
        return bfDB.insert(model.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonCar, map.getClass()));
    }

    public void update() {

    }

    public void delete() {

    }
}
