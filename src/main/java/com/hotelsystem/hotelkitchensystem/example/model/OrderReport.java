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
@Table(name = "OrderReport")
public class OrderReport {

    @Id
    @GeneratedValue
    private int ID;
    private int orderId;
    private int customerId;
    private String customerName;
    private int roomId;
    private int stewardId;
    private String stewardName;
    private String orderDate;
    private int foodId;
    private String foodName;
    private int orderedQty;
    private int ingredientId;
    private String ingredientName;
    private int usedIngredientQty;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setStewardId(int stewardId) {
        this.stewardId = stewardId;
    }

    public void setStewardName(String stewardName) {
        this.stewardName = stewardName;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setUsedIngredientQty(int usedIngredientQty) {
        this.usedIngredientQty = usedIngredientQty;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getStewardId() {
        return stewardId;
    }

    public String getStewardName() {
        return stewardName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getUsedIngredientQty() {
        return usedIngredientQty;
    }
}
