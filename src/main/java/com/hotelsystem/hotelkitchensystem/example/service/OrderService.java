package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import com.hotelsystem.hotelkitchensystem.example.model.FoodOrders;
import com.hotelsystem.hotelkitchensystem.example.repository.FoodOrderRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodOrderRepository foodOrderRepository;


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

}
