package com.rf402503222.myluxurypantry.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.rf402503222.myluxurypantry.models.Ingredient;
import java.util.List;

@androidx.room.Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("DELETE FROM ingredients_table")
    void deleteAllIngredients();

    @Query("SELECT * FROM ingredients_table ORDER BY name ASC")
    LiveData<List<Ingredient>> getAllIngredients();
}