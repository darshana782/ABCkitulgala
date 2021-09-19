package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.InventoryReportDateRangeRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.InventoryReportResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.StockReportResponse;
import com.hotelsystem.hotelkitchensystem.example.service.IngredientReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IngredientReportService ingredientReportService;

    @PostMapping("/generateInventoryReport")
    public List<InventoryReportResponse> findBySelectedDateRange(@RequestBody InventoryReportDateRangeRequest inventoryReportDateRangeRequest){
        return ingredientReportService.findBySelectedDateRange(inventoryReportDateRangeRequest);
    }

    @PostMapping("/StockReport")
    public List<StockReportResponse> findStockUpdatesSelectedDateRange(@RequestBody InventoryReportDateRangeRequest inventoryReportDateRangeRequest){
        return ingredientReportService.findStockUpdatesSelectedDateRange(inventoryReportDateRangeRequest);
    }
}
