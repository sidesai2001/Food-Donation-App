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

public class SignupController implements Initializable 
{
    protected String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private Label nameerr;

    @FXML
    private TextField emailid;

    @FXML
    private Label phonerr;

    @FXML
    private Label emailrr;

    @FXML
    private Label passerr;

    @FXML
    private PasswordField pass;

    @FXML
    private ChoiceBox user;

    @FXML
    private Label userrr;

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
    private void passvisible(MouseEvent event) throws IOException 
    {
        if (label1.isVisible() == false) 
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
    private void backbuttonAction(MouseEvent event) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void signupbuttonAction(MouseEvent event) throws IOException 
    {
        conn = (Connection) mysqlconnect.ConnectDb();
        String uname = name.getText();
        String ph = phone.getText();
        String email = emailid.getText();
        String pswd = pass.getText();
        String us = (String) user.getValue();

        int c = 0;

        if (uname.isEmpty() || pswd.isEmpty() || email.isEmpty() || ph.isEmpty()) 
        {
            if (uname.isEmpty()) 
            {
                nameerr.setText("The Username is required!");
                name.setStyle(errorStyle);
                nameerr.setStyle(errorMessage);
                new animatefx.animation.Shake(name).play();
                c++;
            }
            else 
            {
                nameerr.setText("");
                name.setStyle(successStyle);
            }
            
            if (pswd.isEmpty()) 
            {
                passerr.setText("The Password is required!");
                pass.setStyle(errorStyle);
                passerr.setStyle(errorMessage);
                new animatefx.animation.Shake(pass).play();
                c++;
            }
            else 
            {
                passerr.setText("");
                pass.setStyle(successStyle);
            }
            if (email.isEmpty()) 
            {
                emailrr.setText("The email ID is required!");
                emailid.setStyle(errorStyle);
                emailrr.setStyle(errorMessage);
                new animatefx.animation.Shake(emailid).play();
                c++;
            } 
            else 
            {
                emailrr.setText("");
                emailid.setStyle(successStyle);
            }

            if (ph.isEmpty()) 
            {
                phonerr.setText("The Phone number is required!");
                phone.setStyle(errorStyle);
                phonerr.setStyle(errorMessage);
                new animatefx.animation.Shake(phone).play();
                c++;
            } 
            else 
            {
                phonerr.setText("");
                phone.setStyle(successStyle);
            }
            
            if (us == null) 
            {
                userrr.setText("The user is required!");
                user.setStyle(errorStyle);
                userrr.setStyle(errorMessage);
                new animatefx.animation.Shake(user).play();
                c++;
            } 
            else 
            {
                userrr.setText("");
                user.setStyle(successStyle);
            }
            if (c == 5) 
            {
                nameerr.setText("");
                phonerr.setText("");
                userrr.setText("");
                passerr.setText("");
                emailrr.setText("");
                passerr.setText("All the fields are required!");
                passerr.setStyle(errorMessage);
            }
        } 
        else 
        {
            if (ph.length() != 10 || pswd.length() < 4 || !email.contains("@gmail.com")) 
            {
                if (ph.length() != 10) 
                {
                    phone.setStyle(errorStyle);
                    new animatefx.animation.Shake(phone).play();
                    phonerr.setStyle(errorMessage);
                    phonerr.setText("Enter valid phone number");
                } 
                else 
                {
                    phonerr.setText("");
                    phone.setStyle(successStyle);
                }
                if (pswd.length() <= 4) 
                {
                    pass.setStyle(errorStyle);
                    new animatefx.animation.Shake(pass).play();
                    passerr.setStyle(errorMessage);
                    passerr.setText("Password must be more than 4 characters");
                } 
                else 
                {
                    passerr.setText("");
                    pass.setStyle(successStyle);
                }
                
                if (!email.contains("@gmail.com")) 
                {
                    emailid.setStyle(errorStyle);
                    new animatefx.animation.Shake(emailid).play();
                    emailrr.setStyle(errorMessage);
                    emailrr.setText("Enter valid email ID!");
                } 
                else 
                {
                    emailrr.setText("");
                    emailid.setStyle(successStyle);
                }  
            } 
            else 
            {
                nameerr.setText("");
                phonerr.setText("");
                userrr.setText("");
                passerr.setText("");
                emailrr.setText("");

                String sql ="INSERT INTO signup (S_name,S_phone,S_emailid,S_pass,S_user) VALUES (?,?,?,?,?)";
                
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
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } 
                catch (HeadlessException | SQLException e) 
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        user.getItems().add("Donor");
        user.getItems().add("Volunteer");
    }
}

