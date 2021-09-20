package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddFoodRequest {

        String foodImageUrl;
        String foodName;
        Double foodPrice;
        String foodDescription;
//        int[] foodIngredients;


    public void setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public String getFoodName() {
        return foodName;
    }

        public Double getFoodPrice() {
        return foodPrice;
    }

        public String getFoodDescription() {
        return foodDescription;
    }

//        public int[] getFoodIngredients() {
//            return foodIngredients;
//    }



    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

        public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

        public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

//        public void setFoodIngredients(int[] foodIngredients) {
//        this.foodIngredients = foodIngredients;
//    }


}





//    private String foodName;
//    private Double foodPrice;
//    private String foodDescription;
//    private int[] foodIngredients;
//
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public Double getFoodPrice() {
//        return foodPrice;
//    }
//
//    public String getFoodDescription() {
//        return foodDescription;
//    }
//
//    public Set<Ingredient> getFoodIngredients() {
//        return foodIngredients;
//    }
//
//    public void setFoodName(String foodName) {
//        this.foodName = foodName;
//    }
//
//    public void setFoodPrice(Double foodPrice) {
//        this.foodPrice = foodPrice;
//    }
//
//    public void setFoodDescription(String foodDescription) {
//        this.foodDescription = foodDescription;
//    }
//
//    public void setFoodIngredients(int[] foodIngredients) {
//        this.foodIngredients = foodIngredients;
//    }
//}
