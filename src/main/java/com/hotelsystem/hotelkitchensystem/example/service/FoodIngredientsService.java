package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddRecipeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.model.FoodIngredients;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodIngredientsService {

    @Autowired
    private FoodIngredientRepository foodIngredientRepository;

    public boolean checkIfFoodIngredientExists(int foodId){
        if (foodIngredientRepository.findAllByFoodId(foodId) != null){
            return true;
        }
        return false;
    }

    public void addRecipe(int foodId,AddRecipeDetailsRequest addRecipeDetailsRequest){
//        List<FoodIngredients> foodIngredients = foodIngredientRepository.findAllByFoodId(foodId);
        int[] fiIdList = addRecipeDetailsRequest.getFiIdList();
        int[] ingredientsList = addRecipeDetailsRequest.getIngredientsQtyList();
        int k=0;

        for (int i:fiIdList){
            FoodIngredients foodIngredients = foodIngredientRepository.findByfiId(i);
            foodIngredients.setQty(ingredientsList[k]);
            k++;
            foodIngredientRepository.save(foodIngredients);
        }

    }
}
