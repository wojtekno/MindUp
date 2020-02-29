package com.nowak.wjw.mind_up.model;

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
