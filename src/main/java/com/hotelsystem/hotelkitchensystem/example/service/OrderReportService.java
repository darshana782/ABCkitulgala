package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.InventoryReportDateRangeRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.OrderReportResponse;
import com.hotelsystem.hotelkitchensystem.example.repository.OrderReportRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderReportService {

    @Autowired
    OrderReportRepository orderReportRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<OrderReportResponse> findOrdersBySelectedDateRange(InventoryReportDateRangeRequest inventoryReportDateRangeRequest){
        List<OrderReportResponse> orderReportResponses = new ArrayList<OrderReportResponse>();
        int[] distinctOrderIDs = orderReportRepository.finddistinctOrderIDs(inventoryReportDateRangeRequest.getDateFrom(), inventoryReportDateRangeRequest.getDateTo());

        for (int i=0; i<distinctOrderIDs.length; i++){
            OrderReportResponse orderReportResponse = new OrderReportResponse();

            orderReportResponse.setOrderDate(orderReportRepository.findOrderDateByOrderId(distinctOrderIDs[i]));
            orderReportResponse.setOrderId(distinctOrderIDs[i]);
            orderReportResponse.setRoomId(orderReportRepository.findRoomIdByOrderId(distinctOrderIDs[i]));
            orderReportResponse.setCustomerName(orderReportRepository.findCustomerNameIdByOrderId(distinctOrderIDs[i]));
            orderReportResponse.setOrderPrice(orderRepository.findOrderPriceById(distinctOrderIDs[i]));

            String[] OrderedFoodList = orderReportRepository.findOrderedFoodListByOrderId(distinctOrderIDs[i]);
            orderReportResponse.setOrderedFoods(OrderedFoodList);

                int[] OrderedFoodQtyList = new int[orderReportResponse.getOrderedFoods().length];
                for (int j=0; j<orderReportResponse.getOrderedFoods().length; j++){
                    OrderedFoodQtyList[j] = orderReportRepository.findOrderedFoodQtyByOrderIdAndFoodName(distinctOrderIDs[i], OrderedFoodList[j]);
                    orderReportResponse.setOrderedFoodQty(OrderedFoodQtyList);
                }

            orderReportResponse.setAssignedStewardId(orderReportRepository.findAssignedStewardIdByOrderId(distinctOrderIDs[i]));
            orderReportResponse.setAssignedStewardName(orderReportRepository.findAssignedStewardNameByOrderId(distinctOrderIDs[i]));

            orderReportResponses.add(orderReportResponse);
        }
        return orderReportResponses;
    }



}
