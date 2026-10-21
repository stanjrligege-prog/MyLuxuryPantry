package com.rf402503222.myluxurypantry.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double quantity;
    private String unit;

    // Room requires a constructor
    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Room requires getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}