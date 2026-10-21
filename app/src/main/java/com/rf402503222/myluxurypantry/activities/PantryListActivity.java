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

        // 1. Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new IngredientAdapter();
        recyclerView.setAdapter(adapter);

        // 2. Setup Database (THIS IS WHAT CRASHED - ensure it's here)
        database = AppDatabase.getInstance(this);

        // 3. Setup FAB (Floating Action Button)
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        // 4. Observe LiveData from Database
        database.ingredientDao().getAllIngredients().observe(this, ingredients -> {
            adapter.setIngredients(ingredients);
        });
    }
}