package com.rf402503222.myluxurypantry.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.rf402503222.myluxurypantry.R;
import com.rf402503222.myluxurypantry.database.AppDatabase;
import com.rf402503222.myluxurypantry.models.Ingredient;

public class AddEditIngredientActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.rf402503222.myluxurypantry.EXTRA_ID";
    public static final String EXTRA_NAME = "com.rf402503222.myluxurypantry.EXTRA_NAME";
    public static final String EXTRA_QUANTITY = "com.rf402503222.myluxurypantry.EXTRA_QUANTITY";
    public static final String EXTRA_UNIT = "com.rf402503222.myluxurypantry.EXTRA_UNIT";

    private EditText etName, etQuantity, etUnit;
    private int ingredientId = -1; // Default to -1 (means it's a new ingredient)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        Button btnSave = findViewById(R.id.btnSave);

        // Check if we are editing (data was passed via Intent)
        if (getIntent().hasExtra(EXTRA_ID)) {
            setTitle("Edit Ingredient");
            ingredientId = getIntent().getIntExtra(EXTRA_ID, -1);
            etName.setText(getIntent().getStringExtra(EXTRA_NAME));
            etQuantity.setText(String.valueOf(getIntent().getDoubleExtra(EXTRA_QUANTITY, 0)));
            etUnit.setText(getIntent().getStringExtra(EXTRA_UNIT));
            btnSave.setText("Update Ingredient");
        } else {
            setTitle("Add Ingredient");
        }

        btnSave.setOnClickListener(v -> saveIngredient());
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();

        // 1. Input Validation
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantityStr) || TextUtils.isEmpty(unit)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityStr);
            if (quantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        Ingredient ingredient = new Ingredient(name, quantity, unit);

        // 2. Save or Update
        new Thread(() -> {
            if (ingredientId != -1) {
                // We are updating an existing ingredient
                ingredient.setId(ingredientId);
                AppDatabase.getInstance(this).ingredientDao().update(ingredient);
            } else {
                // We are inserting a new one
                AppDatabase.getInstance(this).ingredientDao().insert(ingredient);
            }

            runOnUiThread(() -> {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}