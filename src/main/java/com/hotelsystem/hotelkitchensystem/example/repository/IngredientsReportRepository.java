package com.hotelsystem.hotelkitchensystem.example.repository;


import com.hotelsystem.hotelkitchensystem.example.model.IngredientsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsReportRepository extends JpaRepository<IngredientsReport, Integer> {
}
