package com.example.webviewtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by abiak on 5/11/17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkStateReceiverListener mListener;
    private boolean mIsDisconnectSent = false;

    public NetworkChangeReceiver(NetworkStateReceiverListener listener) {
        mListener = listener;
    }

    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();

        if(ni != null && ni.isConnectedOrConnecting()) {
            mListener.networkAvailable();
            mIsDisconnectSent = false;
        } else if(!mIsDisconnectSent) {
            mListener.networkUnavailable();
            mIsDisconnectSent = true;
        }

    }

    public interface NetworkStateReceiverListener {
        void networkAvailable();
        void networkUnavailable();
    }
}
