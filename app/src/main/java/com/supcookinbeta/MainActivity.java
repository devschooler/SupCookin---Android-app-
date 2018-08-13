package com.supcookinbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mListViewRecipe = (ListView) findViewById(R.id.main_listview);

    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true ;
}

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                startActivity(new Intent(this, RecipeActivity.class)) ;

                break;

        }

        return true;
}

@Override
    protected void onResume() {

        super.onResume();
        mListViewRecipe.setAdapter(null);

    ArrayList<Recipe> recipes = Utilities.getAllSavedRecipe(this);
    if (recipes == null || recipes.size() == 0 ) {
        Toast.makeText(this,"Vous n'avez pas de recettes enregistrer", Toast.LENGTH_SHORT).show();
        return;
    } else {
        RecipeAdapter na = new RecipeAdapter(this, R.layout.item_recipe, recipes );
        mListViewRecipe.setAdapter(na);


    }

    if(recipes != null && recipes.size() > 0) {
        final RecipeAdapter na = new RecipeAdapter(this, R.layout.item_recipe, recipes);
        mListViewRecipe.setAdapter(na);

        mListViewRecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String fileName = ((Recipe) mListViewRecipe.getItemAtPosition(position)).getDateTime()
                        + Utilities.FILE_EXTENSION;
                Intent viewRecipeIntent = new Intent(getApplicationContext(), RecipeActivity.class);
                viewRecipeIntent.putExtra(Utilities.EXTRAS_RECIPE_FILENAME, fileName);
                startActivity(viewRecipeIntent);
            }
        });
    } else {
        Toast.makeText(getApplicationContext(), "Vous n'avez pas de recettes "
                , Toast.LENGTH_SHORT).show();
    }



}

}
