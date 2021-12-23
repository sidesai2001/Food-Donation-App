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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siddhant Desai
 */
public class SignupController implements Initializable {

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
    /**
     * Initializes the controller class.
     */
   
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //ValidationSupport validationSupport = new ValidationSupport();
    
    @FXML
    private void toLoginAction(MouseEvent event) throws IOException {
        
               // In case the Username and Password fields are left blank then display the error message
        if (emailid.getText().isEmpty() || pass.getText().isEmpty()) {
                invalidDetails.setStyle(errorMessage);

            // When the username and password are blank
            if (emailid.getText().isEmpty() && pass.getText().isEmpty()) {
                invalidDetails.setText("The Login fields are required!");
                emailid.setStyle(errorStyle);
                pass.setStyle(errorStyle);

                new animatefx.animation.Shake(emailid).play();
                new animatefx.animation.Wobble(usersIcon).play();
                new animatefx.animation.Shake(pass).play();
                new animatefx.animation.Wobble(passwordIcon).play();

            } else // When only the username is blank
                if (emailid.getText().isEmpty()) {
                    emailid.setStyle(errorStyle);
                    invalidDetails.setText("The Username or Email is required!");
                    pass.setStyle(successStyle);
                    new animatefx.animation.Shake(emailid).play();
                    new animatefx.animation.Pulse(usersIcon).play();
                } else // When only the password is blank
                    if (pass.getText().isEmpty()) {
                        pass.setStyle(errorStyle);
                        invalidDetails.setText("The Password is required!");
                        emailid.setStyle(successStyle);
                        new animatefx.animation.Shake(pass).play();
                        new animatefx.animation.Wobble(passwordIcon).play();
                    }
        } else // Check if password is less than four characters, if so display error message
            if (pass.getText().length() < 4) {
                invalidDetails.setText("The Password can't be less than 4 characters!");
                invalidDetails.setStyle(errorMessage);
                pass.setStyle(errorStyle);
                new animatefx.animation.FadeIn(pass).play();
                new animatefx.animation.Wobble(passwordIcon).play();
            }
            // If all login details are entered as required then display success message
            else {
                invalidDetails.setText("Login Successful!");
                invalidDetails.setStyle(successMessage);
                emailid.setStyle(successStyle);
                pass.setStyle(successStyle);
                new animatefx.animation.Tada(invalidDetails).play();
                
                root = FXMLLoader.load(getClass().getResource("page1.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //validationSupport.registerValidator(emailid, Validator.createEmptyV alidator("Enter email Id"));
    }    
    
}
