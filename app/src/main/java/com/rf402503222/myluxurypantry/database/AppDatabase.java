package com.rf402503222.myluxurypantry.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.rf402503222.myluxurypantry.models.Ingredient;

@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // This is the link to the DAO
    public abstract IngredientDao ingredientDao();

    // Singleton pattern so we only open the database once
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "pantry_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}