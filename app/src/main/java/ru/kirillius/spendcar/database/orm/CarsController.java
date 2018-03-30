package ru.kirillius.spendcar.database.orm;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kirillius.spendcar.database.models.Cars;
import ru.kirillius.spendcar.database.models.Marks;
import ru.kirillius.spendcar.database.models.Models;
import ru.kirillius.spendcar.database.models.Parameters;

/**
 * Created by Lavrentev on 27.03.2018.
 */

public class CarsController {

    Gson gson;
    BasicFunctionDB bfDB;
    Context context;

    public CarsController(Context context) {
        this.context = context;
        gson = new Gson();
        bfDB = new BasicFunctionDB(context);
    }

    public ArrayList<Cars> get() {
        ArrayList<HashMap<String, String>> list = bfDB.get("cars");
        ArrayList<Cars> result = new ArrayList<>();
        for (HashMap<String, String> item: list) {
            Cars car = gson.fromJson(gson.toJson(item), Cars.class);
            result.add(car);
        }

        return result;
    }

    public Cars findById(int _id) {
        Cars car = null;
        HashMap<String, String> carMap = bfDB.get("cars", "_id = ?", new String[] {String.valueOf(_id)});
        if(carMap!=null) {
            car = gson.fromJson(gson.toJson(carMap), Cars.class);
            Marks markCar = new MarksController(context).findById(car.getMarkId());
            if(markCar!=null)
                car.setMarkCar(markCar);

            Models modelCar = new ModelsController(context).findById(car.getModelId());
            if(modelCar!=null)
                car.setModelCar(modelCar);

            ArrayList<Parameters> listParameters = new ParametersCarController(context).findByIdCar(car.get_id());
            car.setParametersCar(listParameters);
        }

        return car;
    }

    public long create(Cars car) {
        Map<String,Object> map = new HashMap<String,Object>();
        String jsonCar = gson.toJson(car);
        return bfDB.insert(car.getTableName(), (HashMap<String,Object>)gson.fromJson(jsonCar, map.getClass()));
    }

    public void update() {

    }

    public void delete(Integer _id) {
        if(_id==null) {
            bfDB.deleteAll("cars");
        }
    }

    public long getCount() {
        return bfDB.getCount("cars");
    }
}
