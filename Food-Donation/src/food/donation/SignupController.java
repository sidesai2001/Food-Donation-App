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
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
    private Label invalidDetails1;
    
    @FXML
    private ChoiceBox user;
    
    @FXML
    private ImageView passicon;
    
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
       String uname = name.getText();
       String ph = phone.getText();
       String email =  emailid.getText();
       String pswd = pass.getText();
       String us = (String)user.getValue();
       JOptionPane.showMessageDialog(null, uname);
       JOptionPane.showMessageDialog(null, ph);
       JOptionPane.showMessageDialog(null, email);
       JOptionPane.showMessageDialog(null, pswd);
       JOptionPane.showMessageDialog(null, us);
       int c=0;
       if(uname.isEmpty() && pswd.isEmpty() && email.isEmpty() && ph.isEmpty()){
       if(uname.isEmpty() )
       {
                   invalidDetails.setText("The Username is required!");
                   name.setStyle(errorStyle);
                   new animatefx.animation.Shake(name).play();
              } // When only the username is blank
                if (pswd.isEmpty()) {
                    invalidDetails.setText("The Password is required!");
                    pass.setStyle(errorStyle);
                    new animatefx.animation.Shake(pass).play();
                        
                }// When only the password is blank
                    if(email.isEmpty())
                    {
                        invalidDetails.setText("The email ID is required!");
                        emailid.setStyle(errorStyle);
                        new animatefx.animation.Shake(emailid).play();  
                    }
                    
                if (ph.isEmpty()) 
                {
                    invalidDetails.setText("The Phone number is required!");
                    phone.setStyle(errorStyle);
                    new animatefx.animation.Shake(phone).play();
                    
                }
       }
       else{
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
           
           JOptionPane.showMessageDialog(null,"Congratulations, Your account has been successfully created.","Success",JOptionPane.INFORMATION_MESSAGE);
           
           root = FXMLLoader.load(getClass().getResource("signin.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
       catch(HeadlessException | SQLException e)
       {
           JOptionPane.showMessageDialog(null, e);
       }}
       }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user.getItems().add("Donor");
        user.getItems().add("Volunteer");
        
        
    }    
    
}










/*
 try
       {
           pst = (PreparedStatement) conn.prepareStatement(sql);
           uname = name.getText();
           ph = phone.getText();
           email =  emailid.getText();
           pswd = pass.getText();
           us = (String) user.getValue();
           String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                "A-Z]{2,7}$";                
            Pattern pat = Pattern.compile(emailRegex);
  
           pst.setString(1, name.getText());
           pst.setString(2, phone.getText());
           pst.setString(3, emailid.getText());
           pst.setString(4, pass.getText());
           pst.setString(5, (String) user.getValue());
           pst.execute();
           rs = pst.executeQuery();
           JOptionPane.showMessageDialog(null,"ERROR123","Success",JOptionPane.INFORMATION_MESSAGE);
           
           JOptionPane.showMessageDialog(null,"ERROR8567","Success",JOptionPane.INFORMATION_MESSAGE);
           
           if(uname.isEmpty() && pswd.isEmpty() && email.isEmpty() && ph.isEmpty() && us.isEmpty()){
               JOptionPane.showMessageDialog(null,"ERROR890","Success",JOptionPane.INFORMATION_MESSAGE);
               if(uname.isEmpty() && pswd.isEmpty() && email.isEmpty() && ph.isEmpty() && us.isEmpty())
               {
                invalidDetails1.setText("All fields are required!");
                invalidDetails1.setStyle(errorMessage);
                name.setStyle(errorStyle);
                phone.setStyle(errorStyle);
                emailid.setStyle(errorStyle);
                pass.setStyle(errorStyle);
                user.setStyle(errorStyle);
                label1.setStyle(errorStyle);
                new animatefx.animation.Shake(name).play();
                new animatefx.animation.Shake(passicon).play();
                new animatefx.animation.Shake(emailid).play();
                new animatefx.animation.Shake(phone).play();
                new animatefx.animation.Shake(pass).play();
                new animatefx.animation.Shake(user).play();
                new animatefx.animation.Shake(label1).play();   
               }else
                if(uname.isEmpty() ){
                   invalidDetails.setText("The Username is required!");
                   name.setStyle(errorStyle);
                   new animatefx.animation.Shake(name).play();
                   
               }else // When only the username is blank
                if (pswd.isEmpty()) {
                    invalidDetails.setText("The Password is required!");
                    pass.setStyle(errorStyle);
                    new animatefx.animation.Shake(pass).play();
                        
                }else // When only the password is blank
                if (email.isEmpty() && pat.matcher(email).matches() == false) {
                    if(email.isEmpty())
                    {
                        invalidDetails.setText("The email ID is required!");
                        emailid.setStyle(errorStyle);
                        new animatefx.animation.Shake(emailid).play();   
                    }else
                    {
                        invalidDetails.setText("Enter correct email ID!");
                        emailid.setStyle(errorStyle);
                        new animatefx.animation.Shake(emailid).play();  
                        emailid.clear();
                    }
                    
                }else 
                if (ph.isEmpty()) {
                    invalidDetails.setText("The Password is required!");
                    phone.setStyle(errorStyle);
                    new animatefx.animation.Shake(phone).play();
                }else 
                if (us.isEmpty()) {
                    invalidDetails.setText("The Password is required!");
                    user.setStyle(errorStyle);
                    new animatefx.animation.Shake(user).play();
                }   
            
           }
           else{
            JOptionPane.showMessageDialog(null,"Congratulations, Your account has been successfully created.","Success",JOptionPane.INFORMATION_MESSAGE);

            root = FXMLLoader.load(getClass().getResource("signin.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
           }
       }*/
