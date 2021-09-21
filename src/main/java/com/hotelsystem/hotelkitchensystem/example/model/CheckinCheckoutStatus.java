package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CheckinCheckoutStatus")
public class CheckinCheckoutStatus {

    @Id
    @GeneratedValue
    private int id;
    private int customerId;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date checkinDate;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date checkoutDate;
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public String getStatus() {
        return status;
    }
}
