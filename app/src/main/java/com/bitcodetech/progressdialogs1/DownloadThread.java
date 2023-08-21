package com.bitcodetech.progressdialogs1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bitcodetech.progressdialogs1.MainActivity;

public class DownloadThread extends AsyncTask<String, Integer, Float> {

    private Handler handler;

    public DownloadThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Float doInBackground(String [] files) {

        for(String file : files) {


            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Message message = new Message();
                message.obj = new Integer(i);
                message.what = 1;
                handler.sendMessage(message);

                publishProgress(i);
            }
        }
        return 12.12f;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.e("tag", "onProUpdate: " + Thread.currentThread().getName());

    }

    @Override
    protected void onPostExecute(Float res) {
        super.onPostExecute(res);
        Log.e("tag", "onPost: " + Thread.currentThread().getName());

        Message message = new Message();
        message.what = 2;
        message.obj = "/storage/0/Downloads/bitcode/tutorials.zip";

        //handler.handleMessage(message);
        handler.sendMessage(message);
    }
}