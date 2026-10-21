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

    private EditText etName, etQuantity, etUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        // Find our views
        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveIngredient());
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();

        // 1. Input Validation (Required by the rubric!)
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

        // 2. Save to Database (Must be off the main thread!)
        Ingredient newIngredient = new Ingredient(name, quantity, unit);

        new Thread(() -> {
            AppDatabase.getInstance(this).ingredientDao().insert(newIngredient);

            // Go back to the Pantry List after saving
            runOnUiThread(() -> {
                Toast.makeText(this, "Ingredient saved!", Toast.LENGTH_SHORT).show();
                finish(); // This closes the form and returns to the previous screen
            });
        }).start();
    }
}