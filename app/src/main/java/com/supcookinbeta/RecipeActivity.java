package com.supcookinbeta;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    private boolean mIsViewingOrUpdating;
    private long mRecipeCreationTime;
    private String mFileName;
    private Recipe mLoadedRecipe = null;


    private EditText mName ;
    private EditText mType ;
    private EditText mPreparation ;
    private EditText mIngredients ;
    private EditText mRecipeContent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mName = (EditText) findViewById(R.id.Name) ;
        mType = (EditText) findViewById(R.id.Type);
        mPreparation = (EditText) findViewById(R.id.Preparation);
        mIngredients = (EditText) findViewById(R.id.Ingredients);
        mRecipeContent = (EditText) findViewById(R.id.Recipe_content) ;



        mFileName = getIntent().getStringExtra(Utilities.EXTRAS_RECIPE_FILENAME);
        if(mFileName != null && !mFileName.isEmpty() && mFileName.endsWith(Utilities.FILE_EXTENSION)) {
            mLoadedRecipe = Utilities.getRecipeByFileName(getApplicationContext(), mFileName);
            if (mLoadedRecipe != null) {

                mName.setText(mLoadedRecipe.getName());
                mRecipeContent.setText(mLoadedRecipe.getContent());
                mRecipeCreationTime = mLoadedRecipe.getDateTime();
                mIngredients.setText(mLoadedRecipe.getIngredients());
                mPreparation.setText(mLoadedRecipe.getPreparation());
                mType.setText(mLoadedRecipe.getType());
                mIsViewingOrUpdating = true;
            }
        } else {
            mRecipeCreationTime = System.currentTimeMillis();
            mIsViewingOrUpdating = false;
        }
    }

@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_new, menu);
        return true;
}

@Override
    public boolean onOptionsItemSelected(MenuItem item ){

        switch (item.getItemId()) {
            case R.id.action_save:
                saveRecipe();

            case R.id.action_delete:
                deleteNow(); 
                break ;
        }
        return true ;
}

    private void deleteNow() {

        if(mLoadedRecipe == null){

            finish();

        }

        else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("T'es sur ? ")
                    .setMessage("tu vas supprimer" + mName.getText().toString() + "tu es sur ? ")
                    .setPositiveButton("Oui ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteRecipe(getApplicationContext(), mLoadedRecipe.getDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext(), mName.getText().toString() + " est supprimé" , Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    })
                        .setNegativeButton("Non", null);
                         dialog.show();

        }

    }

    private void saveRecipe() {
        Recipe recipe ;
        String name = mName.getText().toString();
        String content = mRecipeContent.getText().toString();
        String type = mType.getText().toString();
        String preparation = mPreparation.getText().toString();
        String ingredients = mIngredients.getText().toString();




        if(name.isEmpty()) {
            Toast.makeText(RecipeActivity.this, "Donnez un nom à votre recette"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if(type.isEmpty()) {
            Toast.makeText(RecipeActivity.this, "Veuillez entrer un type de recette"
                    , Toast.LENGTH_SHORT).show();
            return;
        }
        if(content.isEmpty()) {
            Toast.makeText(RecipeActivity.this, "Veuillez entrer les étapes de la recette"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if(preparation.isEmpty()) {
            Toast.makeText(RecipeActivity.this, "Veuillez entrer votre préparation"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if(ingredients.isEmpty()) {
            Toast.makeText(RecipeActivity.this, "Veuillez entrer vos ingrédients"
                    , Toast.LENGTH_SHORT).show();
            return;
        }



        if(mLoadedRecipe != null) {
            mRecipeCreationTime = mLoadedRecipe.getDateTime();
        } else {
            mRecipeCreationTime = System.currentTimeMillis();
        }




        if(Utilities.saveRecipe(this, new Recipe(mRecipeCreationTime,  name, content , type , preparation , ingredients ))) { //success!

            Toast.makeText(this, "Votre recette a été sauvegardée ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vous n'avez plus d'espace libre   " +
                    "sur votre mobile", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private boolean checkRecipeAltred() {
        if(mIsViewingOrUpdating) {
            return mLoadedRecipe != null && (!mName.getText().toString().equalsIgnoreCase(mLoadedRecipe.getName())
                    || !mRecipeContent.getText().toString().equalsIgnoreCase(mLoadedRecipe.getContent()));
        } else {
            return !mName.getText().toString().isEmpty() || !mRecipeContent.getText().toString().isEmpty();
        }
    }
}




