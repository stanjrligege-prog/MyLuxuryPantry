package com.rf402503222.myluxurypantry.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String requiredIngredients;
    private String instructions;

    public Recipe(String name, String requiredIngredients, String instructions) {
        this.name = name;
        this.requiredIngredients = requiredIngredients;
        this.instructions = instructions;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRequiredIngredients() { return requiredIngredients; }
    public void setRequiredIngredients(String requiredIngredients) { this.requiredIngredients = requiredIngredients; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}