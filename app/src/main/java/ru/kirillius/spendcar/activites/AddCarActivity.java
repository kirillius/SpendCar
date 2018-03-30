package ru.kirillius.spendcar.activites;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import ru.kirillius.spendcar.interfaces.OnSelectItem;
import ru.kirillius.spendcar.services.CommonHelper;
import ru.kirillius.spendcar.services.DialogsHelper;

public class AddCarActivity extends AppCompatActivity implements OnSelectItem {

    Context context;
    Gson gson = new Gson();
    LinearLayout clParameters;
    FloatingActionButton fabSaveCar;
    AutoCompleteTextView etModel, etMark;
    ArrayList<Integer> idsParameters;
    EditText etNameCar, etOdometr, etEatFuel, etYearCreate;
    ImageView ivAvatar;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CommonHelper.setStatusBarColor(this);
        context = AddCarActivity.this;
        idsParameters = new ArrayList<>();

        clParameters = findViewById(R.id.clParameters);
        fabSaveCar = findViewById(R.id.fabSaveCar);
        etModel = findViewById(R.id.etModel);
        etMark = findViewById(R.id.etMark);
        etNameCar = findViewById(R.id.etNameCar);
        etOdometr = findViewById(R.id.etOdometr);
        etEatFuel = findViewById(R.id.etEatFuel);
        etYearCreate = findViewById(R.id.etYearCreate);
        ivAvatar = findViewById(R.id.ivAvatar);

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
                newCar.setOdometr(Float.valueOf(etOdometr.getText().toString()));
                newCar.setEatFuel(Float.valueOf(etEatFuel.getText().toString()));
                newCar.setYearCreate(Integer.valueOf(etYearCreate.getText().toString()));

                if(getIntent().getLongExtra("count", -1)==0)
                    newCar.setMainCar(1);

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
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> items = new ArrayList<String>() {{
                    add("Выбрать из галереи");
                    add("Сфотографировать");
                }};

                dialog = DialogsHelper.dialogWithList(context, ((AddCarActivity) context).getLayoutInflater(), "Сменить фото", items);
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

    @Override
    public void onSelect(String item) {
        if(item==null)
            return;

        if(dialog!=null)
            dialog.dismiss();

        if(item.equals("Выбрать из галереи")) {
            //покажем галерею
            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        }
        else if(item.equals("Сфотографировать")) {
            if ( Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission( context, android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // запишем в лог значения requestCode и resultCode
        // если пришло ОК
        if (requestCode == 1 && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            if (cursor == null || cursor.getCount() < 1) {
                return; // no cursor or no record. DO YOUR ERROR HANDLING
            }

            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            if(columnIndex < 0) // no column index
                return; // DO YOUR ERROR HANDLING

            String picturePath = cursor.getString(columnIndex);

            cursor.close(); // close cursor

            /*photo = decodeFilePath(picturePath.toString());

            List<Bitmap> bitmap = new ArrayList<Bitmap>();
            bitmap.add(photo);
            ImageAdapter imageAdapter = new ImageAdapter(
                    AddIncidentScreen.this, bitmap);
            imageAdapter.notifyDataSetChanged();
            newTagImage.setAdapter(imageAdapter);*/
        }
    }
}
