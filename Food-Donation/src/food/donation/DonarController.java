
package food.donation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import food.donation.Food;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DonarController implements Initializable 
{
    @FXML
    private TextField quantity;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker date;

    @FXML
    private ImageView history;

    @FXML
    private ImageView submit;

    @FXML
    private ImageView remove;

    @FXML
    private ImageView foodde;

    @FXML
    private Label name;

    @FXML
    private TextField foodnm;

    @FXML
    private TableView<Food> historytb;

    @FXML
    private TableColumn<Food, String> hname;

    @FXML
    private TableColumn<Food, String> hfoodname;

    @FXML
    private TableColumn<Food, String> hquantity;

    @FXML
    private TableColumn<Food, String> hadd;

    @FXML
    private TableColumn<Food, String> hdate;

    @FXML
    private TableColumn<Food, String> status;

    @FXML
    private TableView<Food> tableview;

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
    ResultSet rs = null;
    PreparedStatement pst = null;

    String username = FoodDonation.getUser();

    @FXML
    private void backbuttonAction(MouseEvent event) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitbt(MouseEvent event) throws IOException 
    {
        JOptionPane.showMessageDialog(null,"Great! Your data was sent successfully. Thanks","Success",JOptionPane.INFORMATION_MESSAGE);
        root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void historybt(MouseEvent event) throws IOException 
    {
        if (historytb.isVisible() == false) 
        {
            name.setVisible(false);
            historytb.setVisible(true);
            conn = (Connection) mysqlconnect.ConnectDb();
            String sql = "SELECT * FROM donor_food WHERE S_name=?";

            try 
            {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.execute();
                rs = pst.executeQuery();
                
                while (rs.next()) 
                {
                    Food historyf = new Food(rs.getString("S_name"),rs.getString("Food_name"),rs.getString("Number_of_packets"),rs.getString("Address"),rs.getString("Collection_date"),rs.getString("Status"));
                    ObservableList<Food> list = historytb.getItems();
                    list.add(historyf);
                    historytb.setItems(list);

                    hname.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
                    hfoodname.setCellValueFactory(new PropertyValueFactory<Food, String>("foodname"));
                    hquantity.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
                    hadd.setCellValueFactory(new PropertyValueFactory<Food, String>("address"));
                    hdate.setCellValueFactory(new PropertyValueFactory<Food, String>("date"));
                    status.setCellValueFactory(new PropertyValueFactory<Food, String>("status"));
                }
            } 
            catch (HeadlessException | SQLException e) 
            {
                JOptionPane.showMessageDialog(null, e);
            }
        } 
        else 
        {
            name.setVisible(true);
            historytb.setVisible(false);
            historytb.getItems().clear();
        }
    }

    @FXML
    private void fooddetailsAction(MouseEvent event) throws IOException 
    {
        conn = (Connection) mysqlconnect.ConnectDb();
        String sql ="INSERT INTO donor_food (S_name,Food_name,Number_of_packets,address,Collection_date) VALUES (?,?,?,?,?)";

        try 
        {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, foodnm.getText());
            pst.setString(3, quantity.getText());
            pst.setString(4, address.getText());
            pst.setString(5, date.getValue().toString());
            pst.execute();
        } 
        catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }

        Food foodadd = new Food(name.getText(),foodnm.getText(),quantity.getText(),address.getText(),date.getValue().toString(),status.getText());
        ObservableList<Food> list = tableview.getItems();
        list.add(foodadd);
        tableview.setItems(list);
        foodnm.clear();
        address.clear();
        quantity.clear();
        date.getEditor().clear();
    }

    @FXML
    private void remove(MouseEvent event) throws IOException 
    {
        int selectedID = tableview.getSelectionModel().getSelectedIndex();
        Food f = tableview.getSelectionModel().getSelectedItems().get(selectedID);
        
        conn = (Connection) mysqlconnect.ConnectDb();
        String sql ="DELETE FROM donor_food WHERE S_name=? and Food_name=? and Number_of_packets=? and address=? and Collection_date=?";
        
        try 
        {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, f.getName());
            pst.setString(2, f.getFoodname());
            pst.setString(3, f.getQuantity());
            pst.setString(4, f.getAddress());
            pst.setString(5, f.getDate());
            pst.execute();
            tableview.getItems().remove(selectedID);
        } 
        catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        name1.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        foodname.setCellValueFactory(new PropertyValueFactory<Food, String>("foodname"));
        quantity1.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
        add.setCellValueFactory(new PropertyValueFactory<Food, String>("address"));
        date1.setCellValueFactory(new PropertyValueFactory<Food, String>("date"));

        name.setText(username);
    }
}
