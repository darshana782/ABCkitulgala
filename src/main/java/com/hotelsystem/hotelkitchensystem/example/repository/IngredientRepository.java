package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByingredientName(String ingredientName);
    Ingredient findByingredientId(int ingredientId);

    @Query(value = "SELECT x FROM Ingredient x WHERE x.qty <= x.reorderLevel")
    List<Ingredient> findAllHavetoRefill();

    @Query(value = "SELECT x FROM Ingredient x WHERE x.qty > x.reorderLevel")
    List<Ingredient> findAllStillNotHaveToReFillIngredients();
}
