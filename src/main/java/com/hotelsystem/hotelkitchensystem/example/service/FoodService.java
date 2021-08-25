package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddFoodIngredientRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.AddFoodRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Food;
import com.hotelsystem.hotelkitchensystem.example.model.FoodIngredients;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodIngredientRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodIngredientRepository foodIngredientRepository;

    //post method
    public Food saveFood(Food food){
        return foodRepository.save(food);
    }

    public List<Food> saveFoods(List<Food> foods){
        return (List<Food>) foodRepository.saveAll(foods);
    }


    //get method
    public List<Food> getFoods(){
        return foodRepository.findAll();
    }

    public Food getFoodById(int foodId){
        return foodRepository.findById(foodId).orElse(null);
    }

    //get by name
    public Food getFoodByName(String foodName){
        return foodRepository.findByFoodName(foodName);
    }


    //Delete food
    public String deleteFood(int foodId){
        foodRepository.deleteById(foodId);
        return "Food Removed.. "+foodId;
    }

    public boolean checkIfFoodExists(String foodName){
        if (foodRepository.findByFoodName(foodName) != null){
            return true;
        }
        return false;
    }

    //Update food
    public Food updateFood(Food food){
        Food existingFood=foodRepository.findById(food.getFoodId()).orElse(null);
        existingFood.setFoodName(food.getFoodName());
        existingFood.setPrice(food.getPrice());
        existingFood.setAvailableQty(food.getAvailableQty());
        existingFood.setFoodDescription(food.getFoodDescription());
        return foodRepository.save(existingFood);
    }


    public void addfood(AddFoodRequest addFoodRequest){
        Food food = new Food();

        food.setFoodName(addFoodRequest.getFoodName());
        food.setPrice(addFoodRequest.getFoodPrice());
        food.setFoodDescription(addFoodRequest.getFoodDescription());
        foodRepository.save(food);
//
//        foodIngredients.setFoodId(food.getFoodId());
//        foodIngredients.setIngredientId();
    }

    public void addfoodingredients(AddFoodIngredientRequest addFoodIngredientRequest,int foodId){
        int[] fi=addFoodIngredientRequest.getFoodIngredients();

        for(int i:fi){
            FoodIngredients foodIngredients = new FoodIngredients();
            System.out.println(i);
            foodIngredients.setFoodId(foodId);
            foodIngredients.setIngredientId(i);
            foodIngredientRepository.save(foodIngredients);
        }
    }
}
