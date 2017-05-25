package com.rikkei.exercises11_2_3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btGetData;
    private TextView tvDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btGetData = (Button) findViewById(R.id.button);
        tvDisplayData = (TextView) findViewById(R.id.textview);
        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAndShowJson();
            }
        });
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void downloadAndShowJson() {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        String jsonUrl = "http://apps.coreservlets.com/NetworkingSupport/loan-calculator";
        DownloadJsonTask task = new DownloadJsonTask(tvDisplayData);
        task.execute(jsonUrl);
    }
}
