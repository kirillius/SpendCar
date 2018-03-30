package ru.kirillius.spendcar.services;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.adapters.SimpleAdapter;
import ru.kirillius.spendcar.interfaces.OnCompleteAction;


/**
 * Created by Lavrentev on 25.12.2017.
 */

public class DialogsHelper {

    public static void defaultAlertDialog(Context context, String title, String text) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("OK", null).show();
    }

    public static void confirmDialog(Context context, LayoutInflater inflater, String text, final OnCompleteAction listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewDialog = inflater.inflate(R.layout.dialog_confirm, null);
        TextView tvTextDialog;
        Button btnYes, btnNo;

        tvTextDialog = viewDialog.findViewById(R.id.tvTextDialog);
        btnYes = viewDialog.findViewById(R.id.btnYes);
        btnNo = viewDialog.findViewById(R.id.btnNo);

        tvTextDialog.setText(CommonHelper.FilterNullValues(text));
        builder.setView(viewDialog);

        final AlertDialog dialog = builder.create();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null) {
                    listener.onComplete(true);
                    dialog.dismiss();
                }
                else
                    dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null) {
                    listener.onComplete(false);
                    dialog.dismiss();
                }
                else
                    dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static AlertDialog dialogWithList(Context context, LayoutInflater inflater, String title, ArrayList<String> items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewDialog = inflater.inflate(R.layout.dialog_with_list, null);
        TextView tvTitleDialog;
        Button btnCancel;
        RecyclerView rvItems;
        RecyclerView.LayoutManager mLayoutManager;

        tvTitleDialog = viewDialog.findViewById(R.id.tvTitleDialog);
        btnCancel = viewDialog.findViewById(R.id.btnCancel);
        rvItems = viewDialog.findViewById(R.id.rvItems);

        mLayoutManager = new LinearLayoutManager(context);
        rvItems.setLayoutManager(mLayoutManager);
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(new SimpleAdapter(context, items));

        tvTitleDialog.setText(CommonHelper.FilterNullValues(title));
        builder.setView(viewDialog);

        final AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        return dialog;
    }
}
