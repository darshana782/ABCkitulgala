package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class InventoryReportDateRangeRequest {
    private String dateFrom;
    private String dateTo;

    public void setDateFrom(String dateFrom) {  
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
