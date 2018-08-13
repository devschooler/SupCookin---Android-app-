package com.supcookinbeta;

import android.content.Context;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Recipe implements Serializable {

    private long mDateTime;
    private String mName ;
    private String mContent;
    private String mPreparation ;
    private String mType ;
    private String mIngredients ;




    public Recipe(long mRecipeCreationTime, String name, String content, String type, String preparation, String ingredients) {
        mContent = content ;
        mDateTime = mRecipeCreationTime;
        mName = name  ;
        mIngredients = ingredients;
        mType = type ;
        mPreparation = preparation ;
    }

    public void setContent(String content) {

        mContent = content ;
    }

    public void setDateTime(long DateTime){
        mDateTime = DateTime;
    }

    public void setName(String name){
        mName = name;

    }

    public void setType(String type) {
        mType = type;
    }

    public void setPreparation(String preparation) {
        mPreparation = preparation ;
    }

    public void setIngredients(String ingredients) {
        mIngredients = ingredients ;
    }

    public long getDateTime() {
        return mDateTime ;
    }

    public String getName() {
        return mName ;
    }

    public String getIngredients() {
        return mIngredients ;

    }

    public String getType() {
        return mType ;

    }

    public String getPreparation() {
        return mPreparation;
    }

    public String getContent() {
        return  mContent;
    }

    public String getDateTimeFromatted(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));

    }

}


