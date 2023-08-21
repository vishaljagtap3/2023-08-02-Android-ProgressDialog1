package com.bitcodetech.progressdialogs1;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context, String title, String message, int style) {

        ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(style);

        return progressDialog;
    }
}
