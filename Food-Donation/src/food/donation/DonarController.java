/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class DonarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button foodde;
    
    @FXML
    private TextField quantity;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField srno;
    
    @FXML
    private TextField foodnm;

    @FXML
    private Button back;

    @FXML
    private TableView<Food> tableview;

    @FXML
    private TableColumn<Food, String> srnumber;

    @FXML
    private TableColumn<Food, String> foodname;

    @FXML
    private TableColumn<Food, String> quantity1;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void backbuttonAction(MouseEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("selector.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DonarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void fooddetailsAction(MouseEvent event) throws IOException { 
        Food foodadd = new Food(srno.getText(),foodnm.getText(),quantity.getText());
        ObservableList<Food> list = tableview.getItems();
        list.add(foodadd );
        tableview.setItems(list);          
        srno.clear();
        foodnm.clear();
        quantity.clear();
    }
    
    @FXML
    private void remove(MouseEvent event) throws IOException {
       int selectedID = tableview.getSelectionModel().getSelectedIndex();
       tableview.getItems().remove(selectedID);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
       srnumber.setCellValueFactory(new PropertyValueFactory<Food, String>("srnumber"));
       foodname.setCellValueFactory(new PropertyValueFactory<Food, String>("foodname"));
       quantity1.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
    }
    
        
       
               
               
               
               
       
       
       
  
        
    
}
