package com.rf402503222.myluxurypantry.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rf402503222.myluxurypantry.R;
import com.rf402503222.myluxurypantry.adapters.RecipeAdapter;
import com.rf402503222.myluxurypantry.database.AppDatabase;
import com.rf402503222.myluxurypantry.models.Ingredient;
import com.rf402503222.myluxurypantry.models.Recipe;
import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private AppDatabase database;
    private RecipeAdapter adapter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        tvEmpty = findViewById(R.id.tvEmpty);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        database = AppDatabase.getInstance(this);

        adapter.setOnItemClickListener(recipe -> {
            Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
            intent.putExtra("RECIPE_NAME", recipe.getName());
            intent.putExtra("RECIPE_INGREDIENTS", recipe.getRequiredIngredients());
            intent.putExtra("RECIPE_INSTRUCTIONS", recipe.getInstructions());
            startActivity(intent);
        });

        database.ingredientDao().getAllIngredients().observe(this, pantryItems -> {
            database.recipeDao().getAllRecipes().observe(this, allRecipes -> {
                runStrictMatching(pantryItems, allRecipes);
            });
        });
    }

    private void runStrictMatching(List<Ingredient> pantryItems, List<Recipe> allRecipes) {
        List<Recipe> matchedRecipes = new ArrayList<>();

        System.out.println("PANTRY SIZE: " + pantryItems.size());
        System.out.println("RECIPES SIZE: " + allRecipes.size());

        for (Recipe recipe : allRecipes) {
            String[] requiredIngredients = recipe.getRequiredIngredients().split(",");
            boolean hasAllIngredients = true;

            for (String req : requiredIngredients) {
                String requiredIngredient = req.trim().toLowerCase();
                if (requiredIngredient.endsWith("s")) {
                    requiredIngredient = requiredIngredient.substring(0, requiredIngredient.length() - 1);
                }

                boolean foundInPantry = false;
                for (Ingredient pantryItem : pantryItems) {
                    String pantryName = pantryItem.getName().trim().toLowerCase();
                    if (pantryName.endsWith("s")) {
                        pantryName = pantryName.substring(0, pantryName.length() - 1);
                    }

                    if (pantryName.equals(requiredIngredient) || pantryName.contains(requiredIngredient)) {
                        foundInPantry = true;
                        break;
                    }
                }

                if (!foundInPantry) {
                    hasAllIngredients = false;
                    break;
                }
            }

            if (hasAllIngredients) {
                matchedRecipes.add(recipe);
            }
        }
        adapter.setRecipes(matchedRecipes);


        if (matchedRecipes.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
    }
}