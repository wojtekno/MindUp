package com.example.myapplication_java_check.model;

import javax.inject.Inject;

public class ColorObj {

    private ColorEnum mColor;
    private ColorEnum mName;

    @Inject
    public ColorObj (ColorEnum mColor, ColorEnum mName){
        this.mColor = mColor;
        this.mName = mName;
    }

    public ColorEnum getmColor() {
        return mColor;
    }

    public ColorEnum getmName() {
        return mName;
    }
}
