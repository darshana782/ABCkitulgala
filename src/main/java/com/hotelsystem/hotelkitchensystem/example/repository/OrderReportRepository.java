package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.OrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReportRepository extends JpaRepository<OrderReport, Integer> {

    @Query(value = "SELECT o FROM OrderReport o WHERE o.orderDate BETWEEN ?1 AND ?2")
    List<OrderReport> findAllByorderDate(String dateFrom, String dateTo);

    @Query(value = "SELECT COUNT(DISTINCT o.foodId) FROM OrderReport o WHERE o.orderId = ?1 AND o.orderDate BETWEEN ?2 AND ?3")
    int findCountOfOrderedDistinctFoods(int orderId, String dateFrom, String dateTo);

    @Query(value = "SELECT DISTINCT o.orderId  FROM OrderReport o WHERE o.orderDate BETWEEN ?1 AND ?2 ")
    int[] finddistinctOrderIDs(String dateFrom, String dateTo);

    @Query(value = "SELECT DISTINCT(o.orderDate) FROM OrderReport o WHERE o.orderId = ?1")
    String findOrderDateByOrderId(int orderId);

    @Query(value = "SELECT DISTINCT(o.roomId) FROM OrderReport o WHERE o.orderId = ?1")
    int findRoomIdByOrderId(int orderId);

    @Query(value = "SELECT DISTINCT(o.customerName) FROM OrderReport o WHERE o.orderId = ?1")
    String findCustomerNameIdByOrderId(int orderId);

    @Query(value = "SELECT DISTINCT(o.foodName) FROM OrderReport o WHERE o.orderId = ?1")
    String[] findOrderedFoodListByOrderId(int orderId);

    @Query(value = "SELECT DISTINCT(o.orderedQty) FROM OrderReport o WHERE o.orderId = ?1 AND o.foodName=?2")
    int findOrderedFoodQtyByOrderIdAndFoodName(int orderId, String foodName);

    @Query(value = "SELECT DISTINCT(o.stewardId) FROM OrderReport o WHERE o.orderId = ?1")
    int findAssignedStewardIdByOrderId(int orderId);

    @Query(value = "SELECT DISTINCT(o.stewardName) FROM OrderReport o WHERE o.orderId = ?1")
    String findAssignedStewardNameByOrderId(int orderId);



}
