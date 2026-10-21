package com.rf402503222.myluxurypantry.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider; // We'll add the ViewModel later, but we can set up the Activity first
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rf402503222.myluxurypantry.R;
import com.rf402503222.myluxurypantry.adapters.IngredientAdapter;
import com.rf402503222.myluxurypantry.database.AppDatabase;
import com.rf402503222.myluxurypantry.models.Ingredient;

import java.util.List;

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

        database = AppDatabase.getInstance(this);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {

        });

        database.ingredientDao().getAllIngredients().observe(this, ingredients -> {
            adapter.setIngredients(ingredients);
        });
    }
}