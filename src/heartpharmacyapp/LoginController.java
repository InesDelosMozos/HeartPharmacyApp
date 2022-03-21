/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import db.interfaces.UserManager;
import db.sqlite.JPAUserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import pojos.User;

/**
 *
 * @author inesd
 */
public class LoginController {
     private static  UserManager userman= new JPAUserManager();
    @FXML
    private PasswordField passwordlogin;

    @FXML
    private Button loginid;

    @FXML
    private TextField usernamelogin;
     @FXML
    void loginUser(ActionEvent event) {
     Window owner = loginid.getScene().getWindow();
    if (usernamelogin.getText().isEmpty()) {
    showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your email");
    return;
    }
    if (passwordlogin.getText().isEmpty()) {
    showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password again");
    return;
    }
    String username = usernamelogin.getText();
    String password = passwordlogin.getText();

    User user = userman.checkPassword(username, password);
    if (user == null) {
    infoMessage("Please enter correct username or password", null, "Failed");
    } else {
    infoMessage("Successfull log in !!", null, "Message");
    try{
      Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
      Scene scene = new Scene(root);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

      stage.setScene(scene);
      stage.show();
      } catch(Exception e) {
      e.printStackTrace();
      }
    }

    }
     public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
