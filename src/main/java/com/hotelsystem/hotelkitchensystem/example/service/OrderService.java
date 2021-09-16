package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.FoodOrderResponse;
import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import com.hotelsystem.hotelkitchensystem.example.model.Food;
import com.hotelsystem.hotelkitchensystem.example.model.FoodOrders;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodOrderRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.OrderRepository;
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

    public void finishOrder(int orderId){
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("FINISH");
        orderRepository.save(customerOrders);
    }

    public void prepareOrder(int orderId){
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("IN PROGRESS");
        orderRepository.save(customerOrders);
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
