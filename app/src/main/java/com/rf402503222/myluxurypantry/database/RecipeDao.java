package com.rf402503222.myluxurypantry.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import com.rf402503222.myluxurypantry.models.Recipe;
import java.util.List;

@androidx.room.Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Query("SELECT * FROM recipes_table")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT COUNT(*) FROM recipes_table")
    int getCount();
}