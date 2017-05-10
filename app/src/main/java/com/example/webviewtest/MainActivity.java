package com.example.webviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(Constants.WEBVIEW_URL, Constants.URL);
        intent.putExtra(Constants.WEBVIEW_TITLE, "TITLE");
        startActivity(intent);
    }
}
