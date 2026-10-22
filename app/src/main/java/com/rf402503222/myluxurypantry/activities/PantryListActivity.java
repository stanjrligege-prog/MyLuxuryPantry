package com.rf402503222.myluxurypantry.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rf402503222.myluxurypantry.R;
import com.rf402503222.myluxurypantry.adapters.IngredientAdapter;
import com.rf402503222.myluxurypantry.database.AppDatabase;

public class PantryListActivity extends AppCompatActivity {

    private AppDatabase database;
    private IngredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new IngredientAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(ingredient -> {
            Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);

            intent.putExtra(AddEditIngredientActivity.EXTRA_ID, ingredient.getId());
            intent.putExtra(AddEditIngredientActivity.EXTRA_NAME, ingredient.getName());
            intent.putExtra(AddEditIngredientActivity.EXTRA_QUANTITY, ingredient.getQuantity());
            intent.putExtra(AddEditIngredientActivity.EXTRA_UNIT, ingredient.getUnit());

            startActivity(intent);
        });

        adapter.setOnItemLongClickListener(ingredient -> {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Delete Ingredient")
                    .setMessage("Are you sure you want to delete " + ingredient.getName() + "?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        new Thread(() -> {
                            database.ingredientDao().delete(ingredient);
                        }).start();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        database = AppDatabase.getInstance(this);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        database.ingredientDao().getAllIngredients().observe(this, ingredients -> {
            adapter.setIngredients(ingredients);
        });
    }
}