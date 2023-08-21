package com.bitcodetech.progressdialogs1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;
    private TextView txtProgress;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btnDownload);
        txtProgress = findViewById(R.id.txtProgress);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String [] files =  {
                        "https://bitcode.in/android/demos.zip",
                        "https://bitcode.in/android/sdk.zip",
                        "https://bitcode.in/android/notes.zip"
                };

                progressDialog = DialogUtil.getProgressDialog(
                        MainActivity.this,
                        "BitCode",
                        "Downloading...",
                        ProgressDialog.STYLE_HORIZONTAL
                );
                progressDialog.show();
                new DownloadThread(new DownloadHandler())
                        .execute(files);
            }
        });

    }

    class DownloadHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 2) {
                mt((String) msg.obj);
                progressDialog.dismiss();
            }
            else {
                progressDialog.setProgress((Integer) msg.obj);
            }
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}