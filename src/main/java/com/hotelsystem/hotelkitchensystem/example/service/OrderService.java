package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.FoodOrderResponse;
import com.hotelsystem.hotelkitchensystem.example.model.*;
import com.hotelsystem.hotelkitchensystem.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private FoodIngredientRepository foodIngredientRepository;

    public List<CustomerOrders> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<FoodOrderResponse> getByOrderId(int orderId){
        List<FoodOrders> foodOrders = foodOrderRepository.findByorderId(orderId);
        List<FoodOrderResponse> foodOrderResponsesList = new ArrayList<FoodOrderResponse>();
        int foodId = 0;
        for (FoodOrders i:foodOrders){
            foodId = i.getFoodId();
            Food food = foodRepository.findByfoodId(foodId);
            FoodOrderResponse foodOrderResponse = new FoodOrderResponse();

            foodOrderResponse.setFoodId(i.getFoodId());
            foodOrderResponse.setFoodName(food.getFoodName());
            foodOrderResponse.setQty(i.getQty());

            foodOrderResponsesList.add(foodOrderResponse);
        }
        return (List<FoodOrderResponse>) foodOrderResponsesList;
    }

    public CustomerOrders saveCustomerOrders(CustomerOrders customerOrders){
        return orderRepository.save(customerOrders);
    }

    public void addFoodOrder(String orderTime, CustomerFoodOrderRequest customerFoodOrderRequest){
        int customerId = customerFoodOrderRequest.getCustomerId();
        int[] foIdList = customerFoodOrderRequest.getFoIdList();
        int[] qtyList = customerFoodOrderRequest.getQtyList();
        int k=0;
        CustomerOrders customerOrders = orderRepository.findByCusIdAndTime(customerId,orderTime);

        for (int i:foIdList){
            FoodOrders foodOrders = new FoodOrders();
            foodOrders.setOrderId(customerOrders.getOrderId());
            foodOrders.setFoodId(foIdList[k]);
            foodOrders.setQty(qtyList[k]);
            k++;
            foodOrderRepository.save(foodOrders);
        }
    }

    //Change the status of order
    public void finishOrder(int orderId){
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("FINISH");
        orderRepository.save(customerOrders);
    }

    //Update Ingredients on Stock
    public void prepareOrder(int orderId){
        int item_qty = 0, foodId=0, ingredientId=0, ingredient_qty=0, ingredients_for_ordered_food=0, ingredient_qty_before_make_food=0, ingredient_qty_after_make_food=0;
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("IN PROGRESS");
        orderRepository.save(customerOrders);
        List<FoodOrders> foodOrders = foodOrderRepository.findAllByorderId(orderId);

        for (FoodOrders i:foodOrders){
            item_qty = i.getQty();
            foodId = i.getFoodId();
            List<FoodIngredients> foodIngredients = foodIngredientRepository.findAllByFoodId(foodId);
            for (FoodIngredients j:foodIngredients){
                ingredientId = j.getIngredientId();
                ingredient_qty = j.getQty();
                ingredients_for_ordered_food = ingredients_for_ordered_food + (item_qty*ingredient_qty);
                Ingredient ingredient = ingredientRepository.findByingredientId(ingredientId);
                ingredient_qty_before_make_food = ingredient.getQty();
                ingredient_qty_after_make_food = ingredient_qty_before_make_food - ingredients_for_ordered_food;

                ingredient.setQty(ingredient_qty_after_make_food);
                ingredientRepository.save(ingredient);
                ingredients_for_ordered_food=0;
            }
        }
    }

    public List<CustomerOrders> getpendingOrders(){
        String status = "PENDING";
        return (List<CustomerOrders>) orderRepository.findAllBystatus(status);
    }

    public List<CustomerOrders> getInprogressOrders(){
        String status = "IN PROGRESS";
        return (List<CustomerOrders>) orderRepository.findAllBystatus(status);
    }



}
