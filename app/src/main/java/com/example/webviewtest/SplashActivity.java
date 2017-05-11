package com.example.webviewtest;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by abiak on 5/11/17.
 */

public class SplashActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkStateReceiverListener {

    Snackbar snack;
    NetworkChangeReceiver mNetworkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            snack=Snackbar.make(findViewById(android.R.id.content), "No Network connection !", Snackbar.LENGTH_INDEFINITE);

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mNetworkStateReceiver = new NetworkChangeReceiver(this);
        registerReceiver(mNetworkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void networkAvailable() {
        snack.dismiss();
        Intent intent;
        intent = new Intent(SplashActivity.this, WebViewActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void networkUnavailable() {
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        snack.show();
    }
}
