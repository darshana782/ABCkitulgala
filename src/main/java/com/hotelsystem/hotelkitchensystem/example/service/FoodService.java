package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddFoodIngredientRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.AddFoodRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.UpdateFoodPriceRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Food;
import com.hotelsystem.hotelkitchensystem.example.model.FoodIngredients;
import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodIngredientRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodIngredientRepository foodIngredientRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

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

    public List<FoodIngredients> getFoodIngredients(){
        return foodIngredientRepository.findAll();
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

//    public Food updateFood(Food food){
//        Food existingFood=foodRepository.findById(food.getFoodId()).orElse(null);
//        existingFood.setFoodName(food.getFoodName());
//        existingFood.setPrice(food.getPrice());
//        existingFood.setAvailableQty(food.getAvailableQty());
//        existingFood.setFoodDescription(food.getFoodDescription());
//        return foodRepository.save(existingFood);
//    }

    public void updateFoodPrice(UpdateFoodPriceRequest updateFoodPriceRequest){
        int foodId = updateFoodPriceRequest.getFoodId();
        Food food = foodRepository.findByfoodId(foodId);
        food.setPrice(updateFoodPriceRequest.getPrice());
        foodRepository.save(food);
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

//    public List<Food> getAllAddedFoods(AddedFoodRequest addedFoodRequest){
//        int[] addedFoodList = addedFoodRequest.getIdOfOrderedFoods();
//        List<Food> foods = foodRepository.findAll();
//        List<Food> foods2 = new ArrayList<Food>();
//        int x=0, k=0;
//        for (int i:addedFoodList){
//            x=addedFoodList[k];
//            k++;
//            for (Food j:foods){
//                if (x==j.getFoodId()){
//                    foods2.add(j);
//                }
//            }
//
//        }
//        return foods2;
//    }

    public List<FoodIngredients> getFoodIngredientById(int foodId) {
        return foodIngredientRepository.findAllByFoodId(foodId);
    }

    public List<Food> getAvailableFoods() {
        int x=0, dummyIngredientId=0, b=0;
        List<Food> foods = foodRepository.findAll();
        List<Food> foods2 = new ArrayList<Food>();
        for (Food i:foods){
            boolean temp1=false;
            boolean temp2=true;
            x= i.getFoodId();
            System.out.println(x);

            List<FoodIngredients> foodIngredients = foodIngredientRepository.findAll();
            for (FoodIngredients j:foodIngredients){
                if (x==j.getFoodId()){
                    dummyIngredientId = j.getIngredientId();

                    List<Ingredient> ingredient = ingredientRepository.findAll();
                    for (Ingredient z:ingredient){
                        if (dummyIngredientId == z.getIngredientId()){

                            if (z.getQty() > z.getReorderLevel()){
                                temp1=true;
                            }else {
                                temp2=false;
                            }
                        }
                    }
                }
            }
            if (temp1==true && temp2==true){
                System.out.println(i.getFoodName());
                foods2.add(i);
            }
        }
        return foods2;
    }
}
