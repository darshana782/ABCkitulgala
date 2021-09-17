package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AssignStewardRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.FinishOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.FoodOrderResponse;
import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import com.hotelsystem.hotelkitchensystem.example.service.FoodService;
import com.hotelsystem.hotelkitchensystem.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/allOrders")
    public List<CustomerOrders> findAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/findByoderNumber/{orderId}")
    public List<FoodOrderResponse> findOrder(@PathVariable int orderId){
        return (List<FoodOrderResponse>) orderService.getByOrderId(orderId);
    }

    @GetMapping("/pendingOrders")
    public List<CustomerOrders> pendingOrders(){
        return orderService.getpendingOrders();
    }

    @GetMapping("/inprogressOrders")
    public List<CustomerOrders> inprogressOrders(){
        return orderService.getInprogressOrders();
    }


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

    @PostMapping("finishOrder/{orderId}")
    public void finishOrder(@PathVariable int orderId, @RequestBody FinishOrderRequest finishOrderRequest){
        orderService.finishOrder(orderId, finishOrderRequest);

    }

    @PostMapping("prepareOrder/{orderId}")
    public void prepareOrder(@PathVariable int orderId){
        orderService.prepareOrder(orderId);
    }

    @PostMapping("/assignSteward")
    public ResponseEntity assignSteward(@RequestBody AssignStewardRequest assignStewardRequest){
        int orderId = assignStewardRequest.getOrderId();
        String responseMsg = "";
        if(orderService.checkIfAlreadyStewardAssigned(orderId)){
            responseMsg="Steward Already Added";
            return ResponseEntity.ok().body(responseMsg);
        }else {
            orderService.assignSteward(assignStewardRequest);
            responseMsg="Steward Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
    }



//    @PostMapping("/listOfAddedFoods")
//    public ResponseEntity findAddedFoods(@RequestBody AddedFoodRequest addedFoodRequest){
//        return foodService.getAllAddedFoods(addedFoodRequest);
//    }
}
