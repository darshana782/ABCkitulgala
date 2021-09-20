package com.hotelsystem.hotelkitchensystem.example.repository;


import com.hotelsystem.hotelkitchensystem.example.model.IngredientsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsReportRepository extends JpaRepository<IngredientsReport, Integer> {

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    IngredientsReport findByorderIdAndingredientIdForUpdateIngredientQTY(int ingredientId, int orderId);


    @Query(value = "SELECT i.changedQty FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    int findByorderIdAndingredientId(int ingredientId, int orderId);

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    IngredientsReport findByorderIdAndingredientId2(int ingredientId, int orderId);

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.status=?1 AND i.changedDate BETWEEN ?2 AND ?3")
    List<IngredientsReport> findAllBychangedDateAndStatus(String status, String dateFrom, String dateTo);

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.changedDate BETWEEN ?2 AND ?3")
    List<IngredientsReport> findAllByingredientIdAndChangedDate(int ingredientId, String dateFrom, String dateTo);


    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.status=?1 AND i.changedDate BETWEEN ?3 AND ?4 OR i.status=?2 AND i.changedDate BETWEEN ?3 AND ?4 ORDER BY i.changedDate")
    List<IngredientsReport> findAllBychangedDateAndStatusOrderByIngredientId(String status1, String status2, String dateFrom, String dateTo);

}
