package uk.co.rossbeazley.avp.android.log;

import android.util.Log;

public class AndroidLogger implements Logger {
    private static final String TAG = "REDUX";

    @Override
    public void debug(String message) {
        Log.d(TAG, prependThreadName() + message);
    }

    private String prependThreadName() {
        return Thread.currentThread().getName() + ":";
    }
}
