package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AssignStewardRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerFoodOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.FinishOrderRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.FoodOrderResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.StewardTaskResponse;
import com.hotelsystem.hotelkitchensystem.example.model.*;
import com.hotelsystem.hotelkitchensystem.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private FoodIngredientRepository foodIngredientRepository;

    @Autowired
    private StewardGuideRepository stewardGuideRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    IngredientsReportRepository ingredientsReportRepository;

    @Autowired
    OrderReportRepository orderReportRepository;

    public boolean checkIfAlreadyStewardAssigned(int orderId) {
        if (orderRepository.findByorderId(orderId) != null) {
            CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
            if (customerOrders.getAssignedStewardId()!=0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public List<CustomerOrders> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<FoodOrderResponse> getByOrderId(int orderId){
        List<FoodOrders> foodOrders = foodOrderRepository.findByorderId(orderId);
        List<FoodOrderResponse> foodOrderResponsesList = new ArrayList<FoodOrderResponse>();
        int foodId = 0;
        for (FoodOrders i:foodOrders){
            foodId = i.getFoodId();
            Food food = foodRepository.findByfoodId(foodId);
            FoodOrderResponse foodOrderResponse = new FoodOrderResponse();

            foodOrderResponse.setFoodId(i.getFoodId());
            foodOrderResponse.setFoodName(food.getFoodName());
            foodOrderResponse.setQty(i.getQty());

            foodOrderResponsesList.add(foodOrderResponse);
        }
        return (List<FoodOrderResponse>) foodOrderResponsesList;
    }

    public CustomerOrders saveCustomerOrders(CustomerOrders customerOrders){
        return orderRepository.save(customerOrders);
    }

    public void addFoodOrder(String orderTime, CustomerFoodOrderRequest customerFoodOrderRequest){
        int customerId = customerFoodOrderRequest.getCustomerId();
        int[] foIdList = customerFoodOrderRequest.getFoIdList();
        int[] qtyList = customerFoodOrderRequest.getQtyList();
        int k=0;
        CustomerOrders customerOrders = orderRepository.findByCusIdAndTime(customerId,orderTime);

        for (int i:foIdList){
            FoodOrders foodOrders = new FoodOrders();
            foodOrders.setOrderId(customerOrders.getOrderId());
            foodOrders.setFoodId(foIdList[k]);
            foodOrders.setQty(qtyList[k]);
            k++;
            foodOrderRepository.save(foodOrders);
        }
    }

    //Change the status of order
    public void finishOrder(int orderId, FinishOrderRequest finishOrderRequest){
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("FINISH");
        orderRepository.save(customerOrders);

        int sgId = finishOrderRequest.getUserId() + 2;
        StewardGuide stewardGuide = stewardGuideRepository.findBysgId(sgId);
        stewardGuide.setAvailability("AVAILABLE");
        stewardGuideRepository.save(stewardGuide);
//        System.out.println(finishOrderRequest.getUserId());
    }

    //Update Ingredients on Stock
    public void prepareOrder(int orderId){
        int item_qty = 0, foodId=0, ingredientId=0, ingredient_qty=0, ingredients_for_ordered_food=0, ingredient_qty_before_make_food=0, ingredient_qty_after_make_food=0;
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        customerOrders.setStatus("IN PROGRESS");
        orderRepository.save(customerOrders);
        List<FoodOrders> foodOrders = foodOrderRepository.findAllByorderId(orderId);

        for (FoodOrders i:foodOrders){
            item_qty = i.getQty();
            foodId = i.getFoodId();
            List<FoodIngredients> foodIngredients = foodIngredientRepository.findAllByFoodId(foodId);
            for (FoodIngredients j:foodIngredients){
                ingredientId = j.getIngredientId();
                ingredient_qty = j.getQty();
                ingredients_for_ordered_food = ingredients_for_ordered_food + (item_qty*ingredient_qty);
                Ingredient ingredient = ingredientRepository.findByingredientId(ingredientId);
                ingredient_qty_before_make_food = ingredient.getQty();
                ingredient_qty_after_make_food = ingredient_qty_before_make_food - ingredients_for_ordered_food;

                ingredient.setQty(ingredient_qty_after_make_food);
                ingredientRepository.save(ingredient);

                IngredientsReport ingredientsReport3 = ingredientsReportRepository.findByorderIdAndingredientIdForUpdateIngredientQTY(ingredientId, orderId);
                if(ingredientsReport3 != null){

                    IngredientsReport ingredientsReport2 = ingredientsReportRepository.findByorderIdAndingredientId2(ingredientId, orderId);
                    ingredientsReport2.setChangedQty(ingredientsReport2.getChangedQty() + ingredients_for_ordered_food);
                    ingredientsReportRepository.save(ingredientsReport2);

                    ingredients_for_ordered_food=0;

                }else{

                    IngredientsReport ingredientsReport = new IngredientsReport();
                    ingredientsReport.setOrderId(orderId);
                    ingredientsReport.setIngredientId(ingredientId);
                    ingredientsReport.setIngredientName(ingredient.getIngredientName());
                    ingredientsReport.setChangedQty(ingredients_for_ordered_food);
                    ingredientsReport.setChangedDate(customerOrders.getOrderDate());
                    ingredientsReport.setStatus("USED FOR ORDERS");
                    ingredientsReportRepository.save(ingredientsReport);

                    ingredients_for_ordered_food=0;

                }
            }
        }
    }

    public void assignSteward(AssignStewardRequest assignStewardRequest){
        String assigned="ASSIGNED";
        int orderId = assignStewardRequest.getOrderId();
        int empId = assignStewardRequest.getEmpId();
        int userId = empId - 1;
        CustomerOrders customerOrders = orderRepository.findByorderId(orderId);
        StewardGuide stewardGuide = stewardGuideRepository.findByEmployee_empId(empId);

        UserData userData = userDataRepository.findById(customerOrders.getCustomerId());
            String customerName = userData.getFirstName() + " " + userData.getLastName();

            UserData stewardUser = userDataRepository.findById(userId);
            String stewardName = stewardUser.getFirstName() + " " + stewardUser.getLastName();

            customerOrders.setAssignedStewardId(assignStewardRequest.getEmpId());
            orderRepository.save(customerOrders);

            stewardGuide.setAvailability(assigned);
            stewardGuideRepository.save(stewardGuide);

            List<FoodOrders> foodOrders = foodOrderRepository.findAllByorderId(orderId);
            for (FoodOrders i:foodOrders){
                List<FoodIngredients> foodIngredients = foodIngredientRepository.findAllByFoodId(i.getFoodId());
                for (FoodIngredients j:foodIngredients){
                    OrderReport orderReport = new OrderReport();

                    orderReport.setOrderId(orderId);
                    orderReport.setCustomerId(customerOrders.getCustomerId());
                    orderReport.setCustomerName(customerName);
                    orderReport.setRoomId(0);
                    orderReport.setStewardId(empId);
                    orderReport.setStewardName(stewardName);
                    orderReport.setOrderDate(customerOrders.getOrderDate());
                    orderReport.setFoodId(i.getFoodId());
                        Food food = foodRepository.findByfoodId(i.getFoodId());
                    orderReport.setFoodName(food.getFoodName());
                    orderReport.setOrderedQty(i.getQty());
                    orderReport.setIngredientId(j.getIngredientId());
                        Ingredient ingredient = ingredientRepository.findByingredientId(j.getIngredientId());
                    orderReport.setIngredientName(ingredient.getIngredientName());
                    orderReport.setUsedIngredientQty(ingredientsReportRepository.findByorderIdAndingredientId(j.getIngredientId(), orderId));
                    orderReportRepository.save(orderReport);
                }
            }
    }

    public List<CustomerOrders> getpendingOrders(){
        String status = "PENDING";
        return (List<CustomerOrders>) orderRepository.findBystatusAndTime(status);
    }

    public List<CustomerOrders> getInprogressOrders(){
        String status = "IN PROGRESS";
        return (List<CustomerOrders>) orderRepository.findAllBystatus(status);
    }

    public StewardTaskResponse StewardTask(int stewardId){
        stewardId++;
        String statusText = "IN PROGRESS";
        StewardTaskResponse stewardTaskResponse = new StewardTaskResponse();
        CustomerOrders customerOrders = orderRepository.findByStewardIdAndstatus(stewardId, statusText);
        String status = customerOrders.getStatus();
        String text = "FINISH";
//
//        System.out.println(customerOrders.getStatus());
//        System.out.println(customerOrders);

        if(!status.equals(text)){
            int customerId = customerOrders.getCustomerId();
            UserData userData = userDataRepository.findById(customerId);
            String firstName = userData.getFirstName();
            String lastName = userData.getLastName();
            String fullName = firstName + " " + lastName;
            stewardTaskResponse.setOrderId(customerOrders.getOrderId());
            stewardTaskResponse.setRoomId(customerOrders.getRoomId());
            stewardTaskResponse.setCustomerName(fullName);
        }
        return stewardTaskResponse;
    }

}
