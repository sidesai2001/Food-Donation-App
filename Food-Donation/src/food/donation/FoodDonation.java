/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Siddhant Desai
 */
public class FoodDonation extends Application {
    
    public static Boolean isSplashLoaded = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Food Donation");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static String userName;
    
    public static void setUsername(String userName) {

        FoodDonation.userName = userName;
    }
    public static String getUser()
    {
        return(userName);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
