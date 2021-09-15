package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import com.hotelsystem.hotelkitchensystem.example.service.FoodService;
import com.hotelsystem.hotelkitchensystem.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/createOrderId")
    public ResponseEntity createOrderId(@RequestBody CustomerOrders customerOrders){
        //check in ekak thiyena ekekta witharai order ekak danna puluwan
        orderService.saveCustomerOrders(customerOrders);
        return ResponseEntity.ok().body("Order Successfull");
    }

    @PostMapping("/placeOrder/{orderTime}")
    public void placeOrder(@PathVariable String orderTime, @RequestBody CustomerFoodOrderRequest customerFoodOrderRequest){
        String responseMsg;
        orderService.addFoodOrder(orderTime, customerFoodOrderRequest);
    }

//    @PostMapping("/listOfAddedFoods")
//    public ResponseEntity findAddedFoods(@RequestBody AddedFoodRequest addedFoodRequest){
//        return foodService.getAllAddedFoods(addedFoodRequest);
//    }
}
