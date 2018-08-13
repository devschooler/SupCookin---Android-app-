package com.supcookinbeta;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utilities {


    public static final String FILE_EXTENSION = ".bin" ;
    public static final String EXTRAS_RECIPE_FILENAME = "EXTRAS_RECIPE_FILENAME";


    public static boolean saveRecipe(Context context, Recipe recipe) {



        String fileName = String.valueOf(recipe.getDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(recipe);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;





    }

    public static ArrayList<Recipe> getAllSavedRecipe(Context context) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> recipeFiles = new ArrayList<>();

        for(String file : filesDir.list()) {
            if(file.endsWith(FILE_EXTENSION)) {
            recipeFiles.add(file);

            }
            }

            FileInputStream fis;
        ObjectInputStream ois ;

        for (int i = 0 ; i < recipeFiles.size(); i++){
            try {
                fis = context.openFileInput(recipeFiles.get(i));
                ois = new ObjectInputStream(fis);

                recipes.add((Recipe)ois.readObject());

                fis.close();
                ois.close();

            }
            catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
            return null ;
            }
            }
return recipes;
        }




    public static Recipe getRecipeByFileName(Context context, String fileName) {

        File file = new File(context.getFilesDir(), fileName);
        if(file.exists() && !file.isDirectory()) {

            Log.v("UTILITIES", "File exist = " + fileName);

            FileInputStream fis;
            ObjectInputStream ois;

            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                Recipe recipe = (Recipe) ois.readObject();
                fis.close();
                ois.close();

                return recipe;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

    public static void deleteRecipe(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);

        if(file.exists()) {
            file.delete();
        }



    }
}
