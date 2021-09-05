package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.FoodIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodIngredientRepository extends JpaRepository<FoodIngredients, Integer > {

    List<FoodIngredients> findAllByFoodId(int foodId);
    FoodIngredients findByfiId(int i);
//    FoodIngredients findAllByFoodId2(int foodId);

//    @Query(value = "SELECT ingredientId FROM FoodIngredient_Table WHERE foodId=?1 " , nativeQuery = true)
//    int findAllIngredientsByFoodId(int foodId);
}
