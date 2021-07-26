package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddIngredientRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Food;
import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import com.hotelsystem.hotelkitchensystem.example.service.FoodService;
import com.hotelsystem.hotelkitchensystem.example.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class KitchenInventoryController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private IngredientService ingredientService;

    //Food
    //post
    @PostMapping("/addFood")
    public Food addFood(@RequestBody Food food){
        return foodService.saveFood(food);
    }

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
    @PutMapping("/updateFood")
    public Food updateFood(@RequestBody Food food){
        return foodService.updateFood(food);
    }

    @DeleteMapping("/deleteFood/{foodId}")
    public String deleteFood(@PathVariable int foodId){
        return foodService.deleteFood(foodId);
    }


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

    @GetMapping("/ingredientById/{ingredientId}")
    public Ingredient findIngredientById(@PathVariable int ingredientId){
        return ingredientService.getIngredientById(ingredientId);
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


}
