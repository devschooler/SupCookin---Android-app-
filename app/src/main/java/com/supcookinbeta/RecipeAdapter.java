package com.supcookinbeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter <Recipe> {

    public RecipeAdapter(Context context, int resource, ArrayList<Recipe> recipes) {
        super(context, resource, recipes);

    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recipe, null);
        }

       Recipe recipe =  getItem(position);
        if(recipe != null){
            TextView title = (TextView) convertView.findViewById(R.id.list_recipe_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_recipe_date) ;
            TextView content = (TextView) convertView.findViewById(R.id.list_recipe_content_preview);

            title.setText(recipe.getName());
            date.setText(recipe.getDateTimeFromatted(getContext()));
            if (recipe.getContent().length() > 50) {
                content.setText(recipe.getContent().substring(0,50));
            }
            else {
                content.setText(recipe.getContent());

            }

        }
return convertView;
    }

}
