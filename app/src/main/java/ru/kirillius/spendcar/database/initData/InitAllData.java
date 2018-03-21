package ru.kirillius.spendcar.database.initData;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Lavrentev on 21.03.2018.
 */

public class InitAllData extends IntentService {
    public static final String TAG = InitAllData.class.getSimpleName();

    public InitAllData() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        new ParametersInit(this);
    }
}
