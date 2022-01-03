/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.donation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class SigninController implements Initializable {

       // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    
    @FXML
    private Button login;
    
    @FXML
    private Label invalidDetails;

    @FXML
    private ImageView usersIcon;

    @FXML
    private ImageView passwordIcon;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField emailid;
    
    @FXML
    private TextField label1;
    
    /**
     * Initializes the controller class.
     */
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void toLoginAction(MouseEvent event) throws IOException {
        
        conn = (Connection) mysqlconnect.ConnectDb();
        String sql = "Select * from signup where S_emailid = ? and S_pass = ?";
        String usernm, pswd;
        
        
        
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            usernm = emailid.getText();
            pswd = pass.getText();
                      pst.setString(1, usernm);
            pst.setString(2, pswd);
            rs = pst.executeQuery();
            if(rs.next()){
                    String user =rs.getString("S_user");
                    if(user.equals("Donor"))
                    {
                        FoodDonation.setUsername(rs.getString("S_name"));
                        root = FXMLLoader.load(getClass().getResource("donar.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
                    }
                    else
                    {
                        FoodDonation.setUsername(rs.getString("S_name"));
			root = FXMLLoader.load(getClass().getResource("volunteer.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
                    }
				
            }
            
            else{
                // When the username and password are blank
                if (usernm.isEmpty() && pswd.isEmpty()) {
                    invalidDetails.setText("The Login fields are required!");
                    emailid.setStyle(errorStyle);
                    pass.setStyle(errorStyle);

                    new animatefx.animation.Shake(emailid).play();
                    new animatefx.animation.Wobble(usersIcon).play();
                    new animatefx.animation.Shake(pass).play();
                    new animatefx.animation.Wobble(passwordIcon).play();

                }else // When only the username is blank
                if (usernm.isEmpty()) {
                        emailid.setStyle(errorStyle);
                        invalidDetails.setText("The Username or Email is required!");
                        pass.setStyle(successStyle);
                        new animatefx.animation.Shake(emailid).play();
                        new animatefx.animation.Pulse(usersIcon).play();
                }else // When only the password is blank
                if (pswd.isEmpty()) {
                        pass.setStyle(errorStyle);
                        invalidDetails.setText("The Password is required!");
                        emailid.setStyle(successStyle);
                        new animatefx.animation.Shake(pass).play();
                        new animatefx.animation.Wobble(passwordIcon).play();
                }
                else{
                    invalidDetails.setText("Check Email Id and Password");
                    invalidDetails.setStyle(errorMessage);
                    emailid.setStyle(errorStyle);
                    pass.setStyle(errorStyle);
                    new animatefx.animation.FadeIn(pass).play();
                    new animatefx.animation.Wobble(passwordIcon).play();
                    emailid.clear();
                    pass.clear();
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void signupbuttonAction(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
