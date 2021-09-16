package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findByFoodName(String foodName);
    Food findByfoodId(int foodId);
//    Food findAllBy
//    Food findByFoodNameId(String foodName);
}
