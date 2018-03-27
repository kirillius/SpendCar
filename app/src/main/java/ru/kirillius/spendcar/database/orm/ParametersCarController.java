package ru.kirillius.spendcar.database.orm;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.models.Parameters;
import ru.kirillius.spendcar.database.models.ParametersCar;

/**
 * Created by Lavrentev on 27.03.2018.
 */

public class ParametersCarController {
    Gson gson;
    BasicFunctionDB bfDB;
    Context context;

    public ParametersCarController(Context context) {
        this.context = context;
        gson = new Gson();
        bfDB = new BasicFunctionDB(context);
    }

    public ArrayList<ParametersCar> get() {
        return null;
    }

    public ArrayList<Parameters> findByIdCar(int _id) {
        ArrayList<HashMap<String, String>> list = bfDB.findList("parametersCar", "carId = ?", new String[] {String.valueOf(_id)});
        ArrayList<Parameters> result = new ArrayList<>();
        for (HashMap<String, String> item: list) {
            ParametersCar parameterCar = gson.fromJson(gson.toJson(item), ParametersCar.class);
            result.add(new ParametersController(context).findById(parameterCar.getParameterId()));
        }
        return result;
    }

    public long create(ParametersCar parameter) {
        Map<String,Object> map = new HashMap<String,Object>();
        String jsonParameter = gson.toJson(parameter);
        return bfDB.insert(parameter.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonParameter, map.getClass()));
    }

    public void update() {

    }

    public void delete() {

    }
}
