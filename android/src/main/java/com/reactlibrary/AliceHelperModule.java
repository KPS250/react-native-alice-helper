package com.reactlibrary;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class AliceHelperModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public AliceHelperModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "AliceHelper";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        Intent intent = new Intent(android.provider.Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getReactApplicationContext().startActivity(intent);
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
     public void showInternetSettings() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Intent intent = new Intent(android.provider.Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getReactApplicationContext().startActivity(intent);
        }else{
            Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getReactApplicationContext().startActivity(intent);
        }
    }
}
