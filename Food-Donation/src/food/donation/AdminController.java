/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button donar;

    @FXML
    private Button vol;

    @FXML
    private TableView donartable; 
    private TableView voltable; 
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void donar(MouseEvent event) throws IOException {
        
        donartable.setVisible(true); 
    }
    
    @FXML
    private void vol(MouseEvent event) throws IOException {
        
        voltable.setVisible(true);
    }
    
    @FXML
    private void logoutbuttonAction(MouseEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("signin.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
