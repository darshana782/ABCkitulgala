package com.hotelsystem.hotelkitchensystem.example.repository;


import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrders, Integer> {

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.customerId = ?1 AND x.orderTime = ?2")
    CustomerOrders findByCusIdAndTime(int customerId, String orderTime);

    CustomerOrders findByorderId(int orderId);

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.status = ?1 ORDER BY x.orderTime")
    List<CustomerOrders>findBystatusAndTime(String status);

    List<CustomerOrders> findAllBystatus(String status);

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.customerId = ?1 AND x.orderTime = ?2")
    CustomerOrders findByassignedStewardId(int orderId);

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.assignedStewardId = ?1 AND x.status =?2")
    CustomerOrders findByStewardIdAndstatus(int stewardId, String statusText);

    @Query(value = "SELECT x.totalPrice FROM CustomerOrders x WHERE x.orderId=?1")
    float findOrderPriceById(int orderId);

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.customerId=?1 AND x.status<>'FINISH' AND x.orderDate BETWEEN ?2 AND ?3")
    List<CustomerOrders> findAllByCustomerIDDateAndStatus(int userId, String minDate, String maxDate);

    @Query(value = "SELECT x FROM CustomerOrders x WHERE x.customerId=?3 AND x.orderDate BETWEEN ?1 AND ?2")
    List<CustomerOrders> findAllByDateRangeAndCustomerId(String checkinDate, String checkoutDate, int customerId);


}
