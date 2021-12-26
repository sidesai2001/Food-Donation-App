
package food.donation;

import java.sql.*;

public class mysqlconnect 
{
   public Connection conn;
    
    public Connection ConnectDb()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/food_doantion_app","root","");
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
        }  
        return conn;
    }
}

