package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FoodIngredient_Table")
public class FoodIngredients {

    @Id
    @GeneratedValue
    private int fiId;

    private int foodId;

    private int ingredientId;

    public int getFoodId() {
        return foodId;
    }

    public int getIngredientId() {
        return ingredientId;
    }


    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}
