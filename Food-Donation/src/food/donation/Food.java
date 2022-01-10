package food.donation;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class Food 
{
    private String name;
    private String srnumber;
    private String foodname;
    private String quantity;
    private String address;
    private String date;
    private String status;

    public Food(String name,String srnumber,String foodname,String quantity,String address,String date,String status) 
    {
        this.name = name;
        this.srnumber = srnumber;
        this.foodname = foodname;
        this.quantity = quantity;
        this.address = address;
        this.date = date;
        this.status = status;
    }

    Food(String text,String text0,String text1,String text2,String text3,String toString) 
    {
        
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSrnumber() 
    {
        return srnumber;
    }

    public void setSrnumber(String srnumber) 
    {
        this.srnumber = srnumber;
    }

    public String getFoodname() 
    {
        return foodname;
    }

    public void setFoodname(String foodname) 
    {
        this.foodname = foodname;
    }

    public String getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(String quantity) 
    {
        this.quantity = quantity;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getDate() 
    {
        return date;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }
}
