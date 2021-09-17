package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class StewardResponse {
    private int empId;
    private String fName;
    private String lName;

    public StewardResponse() {
        this.empId = empId;
        this.fName = fName;
        this.lName = lName;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getEmpId() {
        return empId;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }
}
