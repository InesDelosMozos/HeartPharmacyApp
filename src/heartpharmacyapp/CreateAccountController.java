/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import java.security.MessageDigest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import db.sqlite.JPAUserManager;
import db.interfaces.UserManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojos.User;

/**
 *
 * @author inesd
 */
public class CreateAccountController {
   private static  UserManager userman= new JPAUserManager();
    
    @FXML
    private PasswordField password;

    @FXML
    private Button create;

    @FXML
    private PasswordField repeatpassword;

    @FXML
    private TextField username;

    @FXML
    void createUser(ActionEvent event) throws IOException {
        Window owner = create.getScene().getWindow();
        if((username.getText().isEmpty())) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your email");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password");
            return;
        }
        if (repeatpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password again");
            return;
        }
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmPassword = this.repeatpassword.getText();
        if(!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password again");
            return;
        }
        try{
            boolean userRepeated= userman.existingUserName(username);
            
            if(userRepeated) {
                infoMessage("ERROR, existing user", null, "Failed");
            }else {
                
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] hash = md.digest();
                User user = new User(username, hash);
                userman.newUser(user);
                Parent root = FXMLLoader.load(getClass().getResource("newPatient.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
   
       }

       public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }

       public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
       
}
