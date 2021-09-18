package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.*;
import com.hotelsystem.hotelkitchensystem.example.model.Food;
import com.hotelsystem.hotelkitchensystem.example.model.FoodIngredients;
import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import com.hotelsystem.hotelkitchensystem.example.service.FoodIngredientsService;
import com.hotelsystem.hotelkitchensystem.example.service.FoodService;
import com.hotelsystem.hotelkitchensystem.example.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3030")
@RestController
public class KitchenInventoryController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private FoodIngredientsService foodIngredientsService;

    //Food
    //post
//    @PostMapping("/addFood")
//    public Food addFood(@RequestBody Food food){
//        return foodService.saveFood(food);
//    }

    @PostMapping("/addFoods")
    public List<Food> addFoods(@RequestBody List<Food> foods){
        return foodService.saveFoods(foods);
    }

    //get
    @GetMapping("/foods")
    public List<Food> findAllFoods(){
        return foodService.getFoods();
    }

    @GetMapping("/foodById/{foodId}")
    public Food findFoodById(@PathVariable int foodId){
        return foodService.getFoodById(foodId);
    }

    //@GetMapping("/food/{foodName}")
//    public Food findFoodByName(@PathVariable String foodName){
//        return foodService.getFoodByName(foodName);
//    }

    //update
//    @PutMapping("/updateFood")
//    public Food updateFood(@RequestBody Food food){
//        return foodService.updateFood(food);
//    }

//    @DeleteMapping("/deleteFood/{foodId}")
//    public String deleteFood(@PathVariable int foodId){
//        return foodService.deleteFood(foodId);
//    }


//Ingredient

//    @PostMapping("/addIngredient")
//    public Ingredient addIngredient(@RequestBody Ingredient ingredient){
//        return ingredientService.saveIngredient(ingredient);
//    }

    @PostMapping("/addIngredients")
    public List<Ingredient> addIngredients(@RequestBody List<Ingredient> ingredients){
        return ingredientService.saveIngredients(ingredients);
    }


    @GetMapping("/ingredients")
    public List<Ingredient> findAllIngredients(){
        return ingredientService.getIngredients();
    }

    @GetMapping("/ingredientsHaveToReFill")
    public List<Ingredient> findAllHaveToReFillIngredients(){
        return ingredientService.getIngredientsHaveToReFillIngredients();
    }

    @GetMapping("/ingredientsStillNotHaveToReFill")
    public List<Ingredient> findAllStillNotHaveToReFillIngredients(){
        return ingredientService.getIngredientsStillNotHaveToReFillIngredients();
    }

    @GetMapping("/ingredientById/{ingredientId}")
    public Ingredient findIngredientById(@PathVariable int ingredientId){
        return ingredientService.getIngredientById(ingredientId);
    }

    @GetMapping("/foodIngredientById/{foodId}")
    public List<FoodIngredients> findFoodIngredientById(@PathVariable int foodId){
        return foodService.getFoodIngredientById(foodId);
    }

    @PostMapping("/addIngredient")
    public ResponseEntity addingre(@RequestBody AddIngredientRequest addIngredientRequest){
        String ingredientName=addIngredientRequest.getIngredientName();
        String responseMsg;
        if(ingredientService.checkIfIngredientExists(ingredientName)){
            responseMsg="Ingredient exists";
        }else {
            ingredientService.addingredient(addIngredientRequest);
            responseMsg="Ingredient Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/updateIngredientQty")
    public ResponseEntity updateIngredientQty(@RequestBody UpdateIngredientRequest updateIngredientRequest){
        String responseMsg;
        ingredientService.updateIngredientQty(updateIngredientRequest);
        responseMsg="Ingredient Updated Successfully";
        return ResponseEntity.ok().body(responseMsg);
    }

    @PostMapping("/updateFoodPrice")
    public ResponseEntity updateIngredientQty(@RequestBody UpdateFoodPriceRequest updateFoodPriceRequest){
        String responseMsg;
        foodService.updateFoodPrice(updateFoodPriceRequest);
        responseMsg="Price Updated Successfully";
        return ResponseEntity.ok().body(responseMsg);
    }


    @PostMapping("/addFood")
    public ResponseEntity addfood(@RequestBody AddFoodRequest addFoodRequest){
        String foodName=addFoodRequest.getFoodName();
        String responseMsg;
//        int[] fi=addFoodRequest.getFoodIngredients();
//
//        String[] strArray = new String[fi.length];
//        for (int i = 0; i < fi.length; i++) {
//            strArray[i] = String.valueOf(fi[i]);
//        }
//
//        String x = Arrays.toString(strArray);

        if (foodService.checkIfFoodExists(foodName)){
            responseMsg="Food Exists";
        }else{
            foodService.addfood(addFoodRequest);
            responseMsg="Food Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/addFoodIngredients")
    public ResponseEntity addfoodingredients(@RequestBody AddFoodIngredientRequest addFoodIngredientRequest){
        String foodName = addFoodIngredientRequest.getFoodName();
        String responseMsg;
        Food name=foodService.getFoodByName(foodName);
//        int foodId;

        if (foodService.checkIfFoodExists(foodName)){
//            addFoodIngredientRequest.setFoodId(name.getFoodId());
            foodService.addfoodingredients(addFoodIngredientRequest,name.getFoodId());
            responseMsg="Food ingredients added";

        }else{
            responseMsg="Food not found";
            return ResponseEntity.badRequest().body(responseMsg);
        }
        return ResponseEntity.ok().body(responseMsg);
    }

    @GetMapping("/foodIngredients")
    public List<FoodIngredients> findAllFoodIngredients(){
        return foodService.getFoodIngredients();
    }


//    @GetMapping("/ingredientByName/{ingredientName")
//    public Ingredient findIngredientByName(@PathVariable String ingredientName){
//        return ingredientService.getIngredientByName(ingredientName);
//    }

    @PutMapping("/updateIngredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.updateIngredient(ingredient);
    }

    @DeleteMapping("/deleteIngredient/{ingredientId}")
    public String deleteIngredient(@PathVariable int ingredientId){
        return ingredientService.deleteIngredient(ingredientId);
    }

    @DeleteMapping("/deleteFood/{foodId}")
    public String deleteFood(@PathVariable int foodId){
        return foodService.deleteFood(foodId);
    }

    @PostMapping("/updateRecipe/{foodId}")
    public ResponseEntity updateRecipe(@PathVariable int foodId, @RequestBody AddRecipeDetailsRequest addRecipeDetailsRequest){
        String responseMsg;
        if (foodIngredientsService.checkIfFoodIngredientExists(foodId)){
            foodIngredientsService.addRecipe(foodId, addRecipeDetailsRequest);
            responseMsg="Recipe added";
        }
        else{
            responseMsg="Food not found";
            return ResponseEntity.badRequest().body(responseMsg);
         }
        return ResponseEntity.ok().body(responseMsg);
    }

    @GetMapping("/cusfoodmenu")
    public List<Food> allAvailableFoods(){
        return foodService.getAvailableFoods();
    }



}
