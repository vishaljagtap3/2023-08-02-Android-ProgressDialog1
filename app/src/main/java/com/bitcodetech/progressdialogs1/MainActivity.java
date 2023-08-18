package com.bitcodetech.progressdialogs1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;
    private TextView txtProgress;

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

                new DownloadThread()
                        .execute(files);
            }
        });

    }

    private class DownloadThread extends AsyncTask<String, Integer, Float> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("tag", "onPre: " + Thread.currentThread().getName());
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Downloading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected Float doInBackground(String [] files) {
            Log.e("tag", "doInBg: " + Thread.currentThread().getName());
            for(String file : files) {

                progressDialog.setMessage(
                        "Downloading " + file
                );

                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    progressDialog.setProgress(i);
                    //btnDownload.setText(file + " " + i + "%");
                    publishProgress(i);
                }
            }
            return 12.12f;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e("tag", "onProUpdate: " + Thread.currentThread().getName());
            btnDownload.setText(values[0] + "%");
        }

        @Override
        protected void onPostExecute(Float res) {
            super.onPostExecute(res);
            Log.e("tag", "onPost: " + Thread.currentThread().getName());
            btnDownload.setText("res = " + res);
            progressDialog.dismiss();
        }
    }

}