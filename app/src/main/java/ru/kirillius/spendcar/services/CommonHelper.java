package ru.kirillius.spendcar.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ru.kirillius.spendcar.R;

/**
 * Created by Lavrentev on 02.10.2017.
 */

public class CommonHelper {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }
        if(inputStream!=null){
            inputStream.close();
        }
        return result;
    }

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager conMgr =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return (netInfo == null) ? false : true;
    }

    public static String FilterNullValues(String str) {
        return (str!=null ? ((!str.equals("null")) ? str : "") : "");
    }

    public static boolean Like(String str, String expr) {
        expr = expr.toLowerCase(); // ignoring locale for now
        expr = expr.replace(".", "\\."); // "\\" is escaped to "\" (thanks, Alan M)
        // ... escape any other potentially problematic characters here
        expr = expr.replace("?", ".");
        expr = expr.replace("%", ".*");
        str = str.toLowerCase();
        return str.matches(expr);
    }

    public static Intent onClickMainMenu(int id, Context context) {
        Intent result = null;
        UserInformationInPhone userInformationInPhone = new UserInformationInPhone(context);
        boolean reqFinish = true;

        switch (id) {
            /*case R.id.record:
                result = new Intent(context, ListMOsActivity.class);
                reqFinish=true;
                break;
            case R.id.myTricks:
                result = new Intent(context, TricksActivity.class);
                reqFinish=true;
                break;
            case R.id.myTickets:
                result = new Intent(context, TicketsActivity.class);
                reqFinish=true;
                break;
            case R.id.myNotifications:
                result = new Intent(context, NotificationsActivity.class);
                reqFinish=true;
                break;
            case R.id.about:
                result = new Intent(context, AboutActivity.class);
                reqFinish=false;
                break;
            case R.id.settings:
                result = new Intent(context, SettingsActivity.class);
                reqFinish=false;
                break;
            case R.id.help:
                result = new Intent(context, HelpActivity.class);
                reqFinish=false;
                break;
            case R.id.exit:
                userInformationInPhone.clearUserInfo();
                PushHelper.sendTokenAfterExit(context);
                result = new Intent(context, LogoutActivity.class);
                reqFinish=true;
                break;*/
        }

        if(result!=null)
            result.putExtra("finish", reqFinish);
        return result;
    }

    public static void setStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void buildNavHeader(final Activity activity, final Context context, NavigationView navigationView, final DrawerLayout drawer) {
        /*View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        final Spinner spPatients = headerView.findViewById(R.id.spPatients);
        TextView tvLabelPersonal = headerView.findViewById(R.id.tvLabelPersonal);

        RelativesHelper.getRelatives(context, new RelativesHelper.OnLoadRelatives() {
            @Override
            public void onLoad(ArrayList<Patients> patients) {
                //ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(context, R.layout.patient_spinner_item, patients.toArray());

                //spinnerArrayAdapter.setDropDownViewResource(R.layout.patients_spinner_dropdown_item);
                //spPatients.setAdapter(spinnerArrayAdapter);
                AdapterSpinnerRelatives adapter = new AdapterSpinnerRelatives(context, activity, R.layout.patient_spinner_item, R.id.tvName, patients);
                spPatients.setAdapter(adapter);

                if(RelativesHelper.getCurrentPatient()!=null) {
                    spPatients.setSelection(RelativesHelper.getIndexPatient(RelativesHelper.getCurrentPatient()));
                }
            }
        });

        tvLabelPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, InfoPatientActivity.class));
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        spPatients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                Patients currentPatient = (Patients) spPatients.getSelectedItem();
                if(++check>1)
                    if (currentPatient.ID != -2) {
                        //то меняем данные пациента
                        RelativesHelper.setCurrentPatient(currentPatient, context);
                        if(activity instanceof ListMOsActivity)
                            activity.startActivity(new Intent(context, ListMOsActivity.class));
                    } else {
                        //нужно добавить пациента
                        activity.startActivity(new Intent(context, NewPatientActivity.class));
                        check=0;
                        spPatients.setSelection(RelativesHelper.getIndexPatient(RelativesHelper.getCurrentPatient()));
                        drawer.closeDrawer(GravityCompat.START);
                    }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buildNavFooter(activity, navigationView);*/
    }

    public static void dialogBeforeClose(Context context, LayoutInflater inflater, final Activity activity) {
        /*DialogsHelper.confirmDialog(context, inflater, context.getResources().getString(R.string.exit_from_app_text), new OnCompleteAction() {
            @Override
            public void onComplete(boolean result) {
                if(result) {
                    setAppRunning(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        activity.finishAndRemoveTask();
                    }
                    else
                        activity.finish();
                }
            }
        });*/
    }
}
