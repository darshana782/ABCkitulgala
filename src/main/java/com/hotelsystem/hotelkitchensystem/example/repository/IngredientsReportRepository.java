package com.hotelsystem.hotelkitchensystem.example.repository;


import com.hotelsystem.hotelkitchensystem.example.model.IngredientsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsReportRepository extends JpaRepository<IngredientsReport, Integer> {

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    IngredientsReport findByorderIdAndingredientIdForUpdateIngredientQTY(int ingredientId, int orderId);


    @Query(value = "SELECT i.changedQty FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    int findByorderIdAndingredientId(int ingredientId, int orderId);

    @Query(value = "SELECT i FROM IngredientsReport i WHERE i.ingredientId=?1 AND i.orderId=?2")
    IngredientsReport findByorderIdAndingredientId2(int ingredientId, int orderId);
}
