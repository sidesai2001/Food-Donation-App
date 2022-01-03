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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    
    @FXML
    private TextField name;
    
    @FXML
    private Label invalidDetails;
    
    @FXML
    private TextField phone;
    
    @FXML
    private TextField emailid;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private ChoiceBox user;
    
    @FXML
    private TextField label1;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private void passvisible(MouseEvent event) throws IOException {
        if(label1.isVisible() == false)
        {
            pass.setVisible(false);
            label1.setVisible(true);
            String password = pass.getText();
            label1.setText(password);
        }
        else
        {
            label1.setVisible(false);
            pass.setVisible(true);
        }
        
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
       String usernm, pswd;
       
       try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           usernm = emailid.getText();
            pswd = pass.getText();
           pst.setString(1, name.getText());
           pst.setString(2, phone.getText());
           pst.setString(3, emailid.getText());
           pst.setString(4, pass.getText());
           pst.setString(5, (String) user.getValue());
           rs = pst.executeQuery();
           if(rs.next()){
            JOptionPane.showMessageDialog(null,"Congratulations, Your account has been successfully created.","Success",JOptionPane.INFORMATION_MESSAGE);

            root = FXMLLoader.load(getClass().getResource("signin.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
           }
           else{
               if(usernm.isEmpty() && pswd.isEmpty()){
                   invalidDetails.setText("The Login fields are required!");
                    emailid.setStyle(errorStyle);
                    pass.setStyle(errorStyle);
                    
                    new animatefx.animation.Shake(emailid).play();
                    new animatefx.animation.Shake(pass).play();
               }else // When only the username is blank
                if (usernm.isEmpty()) {
                        emailid.setStyle(errorStyle);
                        invalidDetails.setText("The Username or Email is required!");
                        pass.setStyle(successStyle);
                        new animatefx.animation.Shake(emailid).play();
                        
                }else // When only the password is blank
                if (pswd.isEmpty()) {
                        pass.setStyle(errorStyle);
                        invalidDetails.setText("The Password is required!");
                        emailid.setStyle(successStyle);
                        new animatefx.animation.Shake(pass).play();
                        
                }
               
           }
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
