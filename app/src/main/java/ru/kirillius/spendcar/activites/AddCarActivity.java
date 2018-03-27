package ru.kirillius.spendcar.activites;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.database.models.Cars;
import ru.kirillius.spendcar.database.models.Marks;
import ru.kirillius.spendcar.database.models.Models;
import ru.kirillius.spendcar.database.models.Parameters;
import ru.kirillius.spendcar.database.models.ParametersCar;
import ru.kirillius.spendcar.database.orm.CarsController;
import ru.kirillius.spendcar.database.orm.MarksController;
import ru.kirillius.spendcar.database.orm.ModelsController;
import ru.kirillius.spendcar.database.orm.ParametersCarController;
import ru.kirillius.spendcar.database.orm.ParametersController;
import ru.kirillius.spendcar.services.CommonHelper;

public class AddCarActivity extends AppCompatActivity {

    Context context;
    Gson gson = new Gson();
    LinearLayout clParameters;
    FloatingActionButton fabSaveCar;
    AutoCompleteTextView etModel, etMark;
    ArrayList<Integer> idsParameters;
    EditText etNameCar;

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
        idsParameters = new ArrayList<>();

        clParameters = findViewById(R.id.clParameters);
        fabSaveCar = findViewById(R.id.fabSaveCar);
        etModel = findViewById(R.id.etModel);
        etMark = findViewById(R.id.etMark);
        etNameCar = findViewById(R.id.etNameCar);

        etMark.setAdapter(getAdapterMark());
        etModel.setAdapter(getAdapterModel());

        //Название машины, фоточка (по дефолту будет тоже), год выпуска, марка, модель, показания одометра
        //заявленый расход бенза
        //какие показатели по дефолту учитывать (стоять будут галки на бензине, масле и фильтрах всех)
        ArrayList<Parameters> listParameters = new ParametersController(context).get();
        for(final Parameters parameter: listParameters) {
            CheckBox cb = new CheckBox(context);
            cb.setText(parameter.getName());
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=0;
                    for(Integer id: idsParameters) {
                        if(id==parameter.get_id()) {
                            idsParameters.remove(index);
                            return;
                        }

                        index++;
                    }

                    idsParameters.add(parameter.get_id());
                }
            });
            clParameters.addView(cb);
        }

        fabSaveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cars newCar = new Cars();
                newCar.setName(etNameCar.getText().toString());

                Marks markCar = new MarksController(context).find(new String[]{"Name"}, new String[]{etMark.getText().toString()});
                if(markCar.get_id()==0) {
                    Marks newMark = new Marks();
                    newMark.setName(etMark.getText().toString());
                    long markId = new MarksController(context).create(newMark);
                    newCar.setMarkId((int)markId);
                }
                else
                    newCar.setMarkId(markCar.get_id());

                Models modelCar = new ModelsController(context).find(new String[]{"Name"}, new String[]{etModel.getText().toString()});
                if(modelCar.get_id()==0) {
                    Models newModel = new Models();
                    newModel.setName(etModel.getText().toString());
                    long modelId = new ModelsController(context).create(newModel);
                    newCar.setModelId((int)modelId);
                }
                else
                    newCar.setModelId(modelCar.get_id());

                long carId = new CarsController(context).create(newCar);
                ParametersCarController parametersCarController = new ParametersCarController(context);
                for(Integer parameterId: idsParameters) {
                    ParametersCar parametersCar = new ParametersCar();
                    parametersCar.setCarId((int)carId);
                    parametersCar.setParameterId(parameterId);
                    parametersCarController.create(parametersCar);
                }

                Toast.makeText(context, "Машина успешно добавлена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayAdapter<String> getAdapterMark() {
        String[] marks = new String[] {"Toyota", "Honda", "Nissan", "Lada"};
        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, marks);
    }

    private ArrayAdapter<String> getAdapterModel() {
        ArrayList<Models> modelsList = new ModelsController(context).get();
        if(modelsList==null)
            return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, new String[0]);

        String[] models = new String[modelsList.size()];
        int i=0;
        for(Models item: modelsList) {
            models[i] = item.getName();
            i++;
        }
        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, models);
    }
}
