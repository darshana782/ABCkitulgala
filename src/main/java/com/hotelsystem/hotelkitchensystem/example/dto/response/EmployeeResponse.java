package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class EmployeeResponse {
    private int empId;
    private String gender;

    public EmployeeResponse(int empId, String gender) {
        this.empId = empId;
        this.gender = gender;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEmpId() {
        return empId;
    }

    public String getGender() {
        return gender;
    }
}
