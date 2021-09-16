package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_Food_Orders")
public class FoodOrders {

    @Id
    @GeneratedValue
    private int foId;
    private int orderId;
    private int foodId;
    private int qty;

    public void setFoId(int foId) {
        this.foId = foId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getFoId() {
        return foId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQty() {
        return qty;
    }
}
