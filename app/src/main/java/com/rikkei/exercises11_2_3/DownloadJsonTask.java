package com.rikkei.exercises11_2_3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bau NV on 5/25/2017.
 */

// Một nhiệm vụ với tham số đầu vào String, và kết quả trả về là String.
public class DownloadJsonTask
        // AsyncTask<Params, Progress, Result>

        extends AsyncTask<String, Void, String> {

    private TextView mTextView;
    private String json;

    public DownloadJsonTask(TextView textView){
        mTextView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        json = "";
        try {
            url = new URL(params[0]);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    // Khi nhiệm vụ hoàn thành, phương thức này sẽ được gọi.
    // Download thành công, update kết quả lên giao diện.
    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            mTextView.setText(jsonObject.getString("monthlyPayment"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
