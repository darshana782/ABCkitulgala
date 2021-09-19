package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.InventoryReportDateRangeRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.InventoryReportResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.StockReportResponse;
import com.hotelsystem.hotelkitchensystem.example.model.Ingredient;
import com.hotelsystem.hotelkitchensystem.example.model.IngredientsReport;
import com.hotelsystem.hotelkitchensystem.example.repository.IngredientRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.IngredientsReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientReportService {
    @Autowired
    IngredientsReportRepository ingredientsReportRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public List<InventoryReportResponse> findBySelectedDateRange(InventoryReportDateRangeRequest inventoryReportDateRangeRequest){
        int usedQty = 0, x=0, sizeOfArray=0;
        boolean stillNotChecked = true;
        String dateFrom = inventoryReportDateRangeRequest.getDateFrom();
        String dateTo = inventoryReportDateRangeRequest.getDateTo();
        List<IngredientsReport> ingredientsReports = ingredientsReportRepository.findAllBychangedDateAndStatus("USED FOR ORDERS" ,dateFrom, dateTo);

        List<InventoryReportResponse> inventoryReportResponse2 = new ArrayList<InventoryReportResponse>();

        for (IngredientsReport i:ingredientsReports){
            sizeOfArray++;
            System.out.println(i.getStatus());
        }

        int[] checkedIngredientID = new int[sizeOfArray];

        for (IngredientsReport i:ingredientsReports){
            List<IngredientsReport> ingredientsReportsWithSameId = ingredientsReportRepository.findAllByingredientIdAndChangedDate(i.getIngredientId(), dateFrom, dateTo);
            for (int k = 0; k<checkedIngredientID.length; k++){
                if(i.getIngredientId() != checkedIngredientID[k]){
                    stillNotChecked = true;
                }else {
                    stillNotChecked =false;
                    break;
                }
            }

            if(stillNotChecked==true){
                checkedIngredientID[x] = i.getIngredientId();
                x++;
                for (IngredientsReport j:ingredientsReportsWithSameId){
                    usedQty = usedQty + j.getChangedQty();
                }

                InventoryReportResponse inventoryReportResponse = new InventoryReportResponse();
                inventoryReportResponse.setIngredientId(i.getIngredientId());
                inventoryReportResponse.setIngredientName(i.getIngredientName());
                Ingredient ingredients = ingredientRepository.findByingredientId(i.getIngredientId());
                inventoryReportResponse.setAvailableQty(ingredients.getQty());
                inventoryReportResponse.setUsedQty(usedQty);

                usedQty=0;

                inventoryReportResponse2.add(inventoryReportResponse);
            }
        }
        return inventoryReportResponse2;
    }

    public List<StockReportResponse> findStockUpdatesSelectedDateRange(InventoryReportDateRangeRequest inventoryReportDateRangeRequest){
        String dateFrom = inventoryReportDateRangeRequest.getDateFrom();
        String dateTo = inventoryReportDateRangeRequest.getDateTo();

        List<IngredientsReport> ingredientsReports = ingredientsReportRepository.findAllBychangedDateAndStatusOrderByIngredientId("STOCK UPDATED", "WASTE" ,dateFrom, dateTo);
        List<StockReportResponse> stockReportResponseReport = new ArrayList<StockReportResponse>();

        for (IngredientsReport i:ingredientsReports){
            StockReportResponse stockReportResponse =new StockReportResponse();
                stockReportResponse.setUpatedDate(i.getChangedDate());
                stockReportResponse.setIngredientId(i.getIngredientId());
                stockReportResponse.setIngredientName(i.getIngredientName());
                stockReportResponse.setUpdatedQty(i.getChangedQty());
                stockReportResponse.setStatus(i.getStatus());

                stockReportResponseReport.add(stockReportResponse);
        }
        return stockReportResponseReport;
    }

}
