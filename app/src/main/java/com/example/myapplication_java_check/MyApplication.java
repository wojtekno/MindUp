package com.example.myapplication_java_check;

import android.app.Application;

import com.example.myapplication_java_check.view_model.AlphabetGameContainer;
//import com.example.myapplication_java_check.view_model.ColorGameContainer;
import com.example.myapplication_java_check.view_model.ColorGameContainer;
import com.example.myapplication_java_check.view_model.DaggerAlphabetGameContainer;
import com.example.myapplication_java_check.view_model.DaggerColorGameContainer;

public class MyApplication extends Application {

    AlphabetGameContainer alphabetGameContainer;
    ColorGameContainer colorGameContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        alphabetGameContainer = createAppContainer();
        colorGameContainer = createColorGameContainer();
    }

    private AlphabetGameContainer createAppContainer(){
        return DaggerAlphabetGameContainer.create();
    }

    AlphabetGameContainer getAlphabetGameContainer() {
        return alphabetGameContainer;
    }

    private ColorGameContainer createColorGameContainer(){
        return DaggerColorGameContainer.create();
    }

    ColorGameContainer getColorGameContainer(){return colorGameContainer;}

}
