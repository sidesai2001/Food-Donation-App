/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;


import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;




/**
 *
 * @author Siddhant Desai
 */
public class Food {
    private String srnumber;
    private String foodname;
    private String quantity;
    private String address;
    private String date;

    public Food(String srnumber, String foodname, String quantity, String address, String date) {
        this.srnumber = srnumber;
        this.foodname = foodname;
        this.quantity = quantity;
        this.address = address;
        this.date = date;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
}
