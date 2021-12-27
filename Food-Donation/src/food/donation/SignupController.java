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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField name;
    
    @FXML
    private TextField phone;
    
    @FXML
    private TextField emailid;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private ChoiceBox user;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Connection conn = null;
    PreparedStatement pst = null;
    
    @FXML
    private void passvisible(MouseEvent event) throws IOException {
       pass.setVisible(true);
    }
    
    @FXML
    private void backbuttonAction(MouseEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("signin.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
    @FXML
    private void signupbuttonAction(MouseEvent event) throws IOException 
    {
       conn =(Connection) mysqlconnect.ConnectDb();
       String sql="INSERT INTO signup (S_name,S_phone,S_emailid,S_pass,S_user) VALUES (?,?,?,?,?)";
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setString(1, name.getText());
           pst.setString(2, phone.getText());
           pst.setString(3, emailid.getText());
           pst.setString(4, pass.getText());
           pst.setString(5, (String) user.getValue());
           pst.execute();
           JOptionPane.showMessageDialog(null, "Saved");
           
           root = FXMLLoader.load(getClass().getResource("signin.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user.getItems().add("Donor");
        user.getItems().add("Volunteer");
        
        
    }    
    
}
