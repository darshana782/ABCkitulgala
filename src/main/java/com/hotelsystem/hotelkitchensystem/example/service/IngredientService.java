package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddIngredientRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.UpdateIngredientRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private FoodRepository foodRepository;

    //post method
    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

public List<Ingredient> saveIngredients(List<Ingredient> ingredients){
        return (List<Ingredient>) ingredientRepository.saveAll(ingredients);
    }

    //get
    public List<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(int ingredientID){
        return ingredientRepository.findById(ingredientID).orElse(null);
    }

    public List<Ingredient> getIngredientsHaveToReFillIngredients(){
        return ingredientRepository.findAllHavetoRefill();
    }

    public List<Ingredient> getIngredientsStillNotHaveToReFillIngredients(){
        return ingredientRepository.findAllStillNotHaveToReFillIngredients();
    }

    //get ny name
//    public Ingredient getIngredientByName(String ingredientName){
//        return ingredientRepository.findByName(ingredientName);
//    }

    //delete ingredient
    public String deleteIngredient(int ingredientId){
        ingredientRepository.deleteById(ingredientId);
        return "Ingredient Removed.. "+ingredientId;
    }

    //update ingredient
    public Ingredient updateIngredient(Ingredient ingredient){
        Ingredient existingIngredient=ingredientRepository.findById(ingredient.getIngredientId()).orElse(null);
        existingIngredient.setIngredientName(ingredient.getIngredientName());
        existingIngredient.setQty(ingredient.getQty());
        existingIngredient.setReorderLevel(ingredient.getReorderLevel());
        return ingredientRepository.save(existingIngredient);
    }


    //check whether Ingredient exists
    public boolean checkIfIngredientExists(String ingredientName) {
        if (ingredientRepository.findByingredientName(ingredientName) != null) {
            return true;
        }
        return false;
    }


    public void addingredient(AddIngredientRequest addIngredientRequest){
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientName(addIngredientRequest.getIngredientName());
        ingredient.setQty(addIngredientRequest.getQty());
        ingredient.setReorderLevel(addIngredientRequest.getReorderLevel());

        ingredientRepository.save(ingredient);
    }

    public void updateIngredientQty(UpdateIngredientRequest updateIngredientRequest){
        int ingredientId = updateIngredientRequest.getIngredientId();
        Ingredient ingredient = ingredientRepository.findByingredientId(ingredientId);
        int availQty = ingredient.getQty();
        availQty = availQty + updateIngredientRequest.getQty();
        ingredient.setQty(availQty);
        ingredientRepository.save(ingredient);
    }




}
