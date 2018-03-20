package ru.kirillius.spendcar.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.kirillius.spendcar.models.CurrentUserInfo;

/**
 * Created by Lavrentev on 02.10.2017.
 */

public class UserInformationInPhone {
    private static final String APP_PREFERENCES = "spendCarUserSettings";
    public static final String APP_PREFERENCES_USER_ID = "userId";
    public static final String APP_PREFERENCES_PIN_CODE = "pinCode";
    public static final String APP_PREFERENCES_CURRENT_USER = "currentUser";
    public static final String APP_PREFERENCES_TOKEN_DEVICE = "tokenDevice";
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    private SharedPreferences mSettings;
    private Context context;

    public UserInformationInPhone(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void clearUserInfo() {
        mSettings.edit().remove(APP_PREFERENCES_PIN_CODE).commit();
        mSettings.edit().remove(APP_PREFERENCES_USER_ID).commit();
        mSettings.edit().remove(APP_PREFERENCES_CURRENT_USER).commit();
    }

    public void saveUserInfo(CurrentUserInfo infoUser){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_USER_ID, infoUser.ID);
        editor.putString(APP_PREFERENCES_CURRENT_USER, gson.toJson(infoUser));
        editor.apply();
    }

    public void saveUserPinCode(String pin) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_PIN_CODE, CommonHelper.md5Custom(pin));
        editor.apply();
    }

    public String getUserId() {
        return mSettings.getString(APP_PREFERENCES_USER_ID, null);
    }

    public CurrentUserInfo getUserInfo() {
        if(mSettings.contains(APP_PREFERENCES_CURRENT_USER)){
            CurrentUserInfo userInfo = gson.fromJson(mSettings.getString(APP_PREFERENCES_CURRENT_USER, null), CurrentUserInfo.class);
            return userInfo;
        }
        else
            return null;
    }

    public boolean checkPin(String pinCode) {
        if(mSettings.contains(APP_PREFERENCES_PIN_CODE)){
            return CommonHelper.md5Custom(pinCode).equals(mSettings.getString(APP_PREFERENCES_PIN_CODE, null));
        } else {
            return false;
        }
    }

    public boolean existsUser() {
        return (mSettings.contains(APP_PREFERENCES_PIN_CODE)) ? true : false;
    }

    public void setTokenDevice(String token) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_TOKEN_DEVICE, token);
        editor.apply();
    }

    public String getTokenDevice() {
        if(mSettings.contains(APP_PREFERENCES_TOKEN_DEVICE)){
            return mSettings.getString(APP_PREFERENCES_TOKEN_DEVICE, null);
        } else {
            return null;
        }
    }
}
