package com.rf402503222.myluxurypantry.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.rf402503222.myluxurypantry.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvIngredients = findViewById(R.id.tvDetailIngredients);
        TextView tvInstructions = findViewById(R.id.tvDetailInstructions);

        String name = getIntent().getStringExtra("RECIPE_NAME");
        String ingredients = getIntent().getStringExtra("RECIPE_INGREDIENTS");
        String instructions = getIntent().getStringExtra("RECIPE_INSTRUCTIONS");

        if (name != null) {
            tvName.setText(name);
            tvIngredients.setText(ingredients);
            tvInstructions.setText(instructions);
            setTitle(name);
        }
    }
}