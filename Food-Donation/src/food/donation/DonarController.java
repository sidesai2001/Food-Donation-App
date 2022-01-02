/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import food.donation.Food;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private TextArea address;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField srno;
    
    @FXML
    private TextField name;
    
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
    private TableColumn<Food, String> add;

    @FXML
    private TableColumn<Food, String> quantity1;
    
    @FXML
    private TableColumn<Food, String> name1;
    
    @FXML
    private TableColumn<Food, String> date1;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection conn = null;
    PreparedStatement pst = null;

    @FXML
    private void backbuttonAction(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("selector.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    private void submitbt(MouseEvent event) throws IOException 
    {
        JOptionPane.showMessageDialog(null,"Great! Your data was sent successfully. Thanks","Success",JOptionPane.INFORMATION_MESSAGE);    
        root = FXMLLoader.load(getClass().getResource("selector.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void fooddetailsAction(MouseEvent event) throws IOException { 
        
       conn =(Connection) mysqlconnect.ConnectDb();
       String sql="INSERT INTO donor_food (S_name,Srno,Food_name,Number_of_packets,address,Collection_date) VALUES (?,?,?,?,?,?)";
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setString(1, name.getText());
           pst.setString(2, srno.getText());
           pst.setString(3, foodnm.getText());
           pst.setString(4, quantity.getText());
           pst.setString(5, address.getText());
           pst.setString(6, date.getValue().toString());
           pst.execute();       
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
       
        Food foodadd = new Food(name.getText(),srno.getText(),foodnm.getText(),quantity.getText(),address.getText(),date.getValue().toString());
        ObservableList<Food> list = tableview.getItems();
        list.add(foodadd );
        tableview.setItems(list);     
        name.clear();
        srno.clear();
        foodnm.clear();
        address.clear();
        quantity.clear();
        date.getEditor().clear();
    }
    
    @FXML
    private void remove(MouseEvent event) throws IOException {
       int selectedID = tableview.getSelectionModel().getSelectedIndex();
       Food f=tableview.getSelectionModel().getSelectedItems().get(selectedID);
       tableview.getItems().remove(selectedID);
       conn =(Connection) mysqlconnect.ConnectDb();
       String sql="DELETE FROM donor_food WHERE S_name=? and Srno=? and Food_name=? and Number_of_packets=? and address=? and Collection_date=?" ;
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setString(1, f.getName());
           pst.setString(2, f.getSrnumber());
           pst.setString(3, f.getFoodname());
           pst.setString(4, f.getQuantity());
           pst.setString(5, f.getAddress());
           pst.setString(6, f.getDate());
           pst.execute();      
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
       name1.setCellValueFactory(new PropertyValueFactory<Food, String>("name")); 
       srnumber.setCellValueFactory(new PropertyValueFactory<Food, String>("srnumber"));
       foodname.setCellValueFactory(new PropertyValueFactory<Food, String>("foodname"));
       quantity1.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
       add.setCellValueFactory(new PropertyValueFactory<Food, String>("address"));
       date1.setCellValueFactory(new PropertyValueFactory<Food, String>("date"));
       
    }
    
        
       
               
               
               
               
       
       
       
  
        
    
}
