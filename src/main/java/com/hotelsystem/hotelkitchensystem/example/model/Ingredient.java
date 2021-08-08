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
@Table(name = "Ingredient_Table")
public class Ingredient {

    @Id
    @GeneratedValue
        private int ingredientId;
    private String ingredientName;
    private int qty;
    private int reorderLevel;
}
