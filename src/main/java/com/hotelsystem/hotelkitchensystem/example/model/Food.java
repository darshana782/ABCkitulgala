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
@Table(name = "Food_Table")
public class Food {

    @Id
    @GeneratedValue
    private int foodId;
    private String foodName;
    private double price;
    private int availableQty;
    private String foodDescription;
    private String foodImageUrl;

    public void setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }


}



