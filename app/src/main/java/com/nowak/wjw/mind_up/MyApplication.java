package com.nowak.wjw.mind_up;

import android.app.Application;

import com.nowak.wjw.mind_up.view_model.AlphabetGameContainer;
import com.nowak.wjw.mind_up.view_model.ColorGameContainer;
import com.nowak.wjw.mind_up.view_model.DaggerAlphabetGameContainer;
import com.nowak.wjw.mind_up.view_model.DaggerColorGameContainer;

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
