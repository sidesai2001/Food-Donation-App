/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class VolunteerController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TableView<Food> volunteertb;

    @FXML
    private TableColumn<Food, String> name1;

    @FXML
    private TableColumn<Food, String> srnumber;

    @FXML
    private TableColumn<Food, String> foodname;

    @FXML
    private TableColumn<Food, String> quantity1;

    @FXML
    private TableColumn<Food, String> add;

    @FXML
    private TableColumn<Food, String> date1;

      @FXML
    private TableColumn<Food, String> status;

    @FXML
    private Button details;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    private void backbuttonAction(MouseEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("signin.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
    @FXML
    private void detailsvisible(MouseEvent event) throws IOException {
       {
        if(volunteertb.isVisible() == false)
        {
            volunteertb.setVisible(true);
            conn =(Connection) mysqlconnect.ConnectDb();
       String sql="SELECT * FROM donor_food ";
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.execute();   
           rs = pst.executeQuery();
           while(rs.next())
           {
                Food historyf = new Food(rs.getString("S_name"),rs.getString("Srno"),rs.getString("Food_name"),rs.getString("Number_of_packets"),rs.getString("Address"),rs.getString("Collection_date"),rs.getString("Status"));
                ObservableList<Food> list = volunteertb.getItems();
                list.add(historyf );
                volunteertb.setItems(list);   
              
                name1.setCellValueFactory(new PropertyValueFactory<Food, String>("name")); 
                srnumber.setCellValueFactory(new PropertyValueFactory<Food, String>("srnumber"));
                foodname.setCellValueFactory(new PropertyValueFactory<Food, String>("foodname"));
                quantity1.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
                add.setCellValueFactory(new PropertyValueFactory<Food, String>("address"));
                date1.setCellValueFactory(new PropertyValueFactory<Food, String>("date"));
                status.setCellValueFactory(new PropertyValueFactory<Food, String>("status"));
                
           }
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
            
        }
        else{
            volunteertb.setVisible(false);
            volunteertb.getItems().clear();
        }
    }
    
    }
    
    @FXML
    private void collectionbt(MouseEvent event) throws IOException 
    {
       int selectedID = volunteertb.getSelectionModel().getSelectedIndex();
       volunteertb.getSelectionModel().getSelectedItems().get(selectedID);
       conn =(Connection) mysqlconnect.ConnectDb();
       String sql="UPDATE donor_food SET status='Collected' where S_name=? and Srno=? and Food_name=? and Number_of_packets=? and address=? and Collection_date=? and status=?";
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setString(1, name1.getCellData(selectedID));
           pst.setString(2, srnumber.getCellData(selectedID));
           pst.setString(3, foodname.getCellData(selectedID));
           pst.setString(4, quantity1.getCellData(selectedID));
           pst.setString(5, add.getCellData(selectedID));
           pst.setString(6, date1.getCellData(selectedID));
           pst.setString(7, status.getCellData(selectedID));
           pst.execute();   
           volunteertb.refresh();
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
