package com.example.myapplication_java_check;

import android.app.Application;

import com.example.myapplication_java_check.view_model.AppContainer;
import com.example.myapplication_java_check.view_model.DaggerAppContainer;

public class MyApplication extends Application {

    AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = createAppContainer();
    }

    private AppContainer createAppContainer(){
        return DaggerAppContainer.create();
    }

    AppContainer getAppContainer() {
        return appContainer;
    }
}
