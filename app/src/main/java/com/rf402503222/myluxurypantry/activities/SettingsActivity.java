package com.rf402503222.myluxurypantry.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.rf402503222.myluxurypantry.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SettingsPrefs";
    private static final String KEY_EXPIRING_ALERTS = "expiring_alerts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchMaterial switchExpiring = findViewById(R.id.switchExpiring);

        // Load saved preference
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isAlertsEnabled = prefs.getBoolean(KEY_EXPIRING_ALERTS, true); // Default to true
        switchExpiring.setChecked(isAlertsEnabled);

        // Save preference when toggled
        switchExpiring.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_EXPIRING_ALERTS, isChecked);
            editor.apply();
        });
    }
}