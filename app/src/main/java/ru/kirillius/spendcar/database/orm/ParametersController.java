package ru.kirillius.spendcar.database.orm;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.models.Parameters;

/**
 * Created by Lavrentev on 27.03.2018.
 */

public class ParametersController {
    Gson gson;
    BasicFunctionDB bfDB;
    Context context;

    public ParametersController(Context context) {
        this.context = context;
        gson = new Gson();
        bfDB = new BasicFunctionDB(context);
    }

    public ArrayList<Parameters> get() {
        ArrayList<HashMap<String, String>> list = bfDB.get("parameters");
        ArrayList<Parameters> result = new ArrayList<>();
        for (HashMap<String, String> item: list) {
            Parameters parameter = gson.fromJson(gson.toJson(item), Parameters.class);
            result.add(parameter);
        }

        return result;
    }

    public Parameters findById(int _id) {
        Parameters parameter = null;
        HashMap<String, String> parameterMap = bfDB.get("parameters", "_id = ?", new String[] {String.valueOf(_id)});
        if(parameterMap!=null)
            parameter = gson.fromJson(gson.toJson(parameterMap), Parameters.class);

        return parameter;
    }

    public long create(Parameters parameter) {
        Map<String,Object> map = new HashMap<String,Object>();
        String jsonParameter = gson.toJson(parameter);
        return bfDB.insert(parameter.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonParameter, map.getClass()));
    }

    public void update() {

    }

    public void delete() {

    }
}
