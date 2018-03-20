package ru.kirillius.spendcar.services;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import ru.kirillius.spendcar.interfaces.OnCompleteAction;


/**
 * Created by Lavrentev on 25.12.2017.
 */

public class DialogsHelper {
    public static void DefaultNotification(Context context, LayoutInflater inflater, String title, String text, final OnCompleteAction listener) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewDialog = inflater.inflate(R.layout.dialog_default, null);
        TextView tvTitleDialog, tvText;
        Button btnClose;

        tvTitleDialog = viewDialog.findViewById(R.id.tvTitleDialog);
        tvText = viewDialog.findViewById(R.id.tvText);
        btnClose = viewDialog.findViewById(R.id.btnClose);

        tvTitleDialog.setText(CommonHelper.FilterNullValues(title));
        tvText.setText(CommonHelper.FilterNullValues(text));

        builder.setView(viewDialog);
        final AlertDialog dialog = builder.create();
        btnClose.setOnClickListener(new View.OnClickListener() {
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

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if(listener!=null)
                    listener.onComplete(true);
            }
        });

        dialog.show();*/
    }

    public static void NotificationAboutRequiredParams(Context context, LayoutInflater inflater, String title, String text, final OnCompleteAction listener) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewDialog = inflater.inflate(R.layout.dialog_about_required, null);
        TextView tvTitleDialog, tvText;
        Button btnClose, btnExit;

        tvTitleDialog = viewDialog.findViewById(R.id.tvTitleDialog);
        tvText = viewDialog.findViewById(R.id.tvText);
        btnClose = viewDialog.findViewById(R.id.btnClose);
        btnExit = viewDialog.findViewById(R.id.btnExit);

        tvTitleDialog.setText(CommonHelper.FilterNullValues(title));
        tvText.setText(CommonHelper.FilterNullValues(text));

        builder.setView(viewDialog);
        final AlertDialog dialog = builder.create();
        btnExit.setOnClickListener(new View.OnClickListener() {
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

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();*/
    }

    public static void DefaultNotification(Context context, LayoutInflater inflater, String title, String text, String titleButton, final OnCompleteAction listener) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewDialog = inflater.inflate(R.layout.dialog_default, null);
        TextView tvTitleDialog, tvText;
        Button btnClose;

        tvTitleDialog = viewDialog.findViewById(R.id.tvTitleDialog);
        tvText = viewDialog.findViewById(R.id.tvText);
        btnClose = viewDialog.findViewById(R.id.btnClose);
        if(titleButton!=null)
            btnClose.setText(titleButton);

        tvTitleDialog.setText(CommonHelper.FilterNullValues(title));
        tvText.setText(CommonHelper.FilterNullValues(text));

        builder.setView(viewDialog);
        final AlertDialog dialog = builder.create();
        btnClose.setOnClickListener(new View.OnClickListener() {
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

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if(listener!=null)
                    listener.onComplete(true);
            }
        });

        dialog.show();*/
    }


    public static void DefaultAlertDialog(Context context, String title, String text) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("OK", null).show();
    }

    public static void confirmDialog(Context context, LayoutInflater inflater, String text, final OnCompleteAction listener) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);

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

        dialog.show();*/
    }
}
