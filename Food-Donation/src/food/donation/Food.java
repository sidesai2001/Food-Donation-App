/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

/**
 *
 * @author Siddhant Desai
 */
public class Food {
    private String srnumber;
    private String foodname;
    private String quantity;

    public Food(String srnumber, String foodname, String quantity) {
        this.srnumber = srnumber;
        this.foodname = foodname;
        this.quantity = quantity;
    }

    Food() {
        
    }

    Food(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSrnumber() {
        return srnumber;
    }

    public void setSrnumber(String srnumber) {
        this.srnumber = srnumber;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

   
   
   
    
}
