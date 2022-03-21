/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author inesd
 */
public class PantallaInicioController {
    
    @FXML
    private Button createAccount;

    @FXML
    private Button login;

    @FXML
    void logIn(ActionEvent event) {
    try{
	  Parent root = FXMLLoader.load(getClass().getResource("login.fxml")); 
	  Scene scene = new Scene(root);
	  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		
	  stage.setScene(scene);
	  stage.show();
	  } catch(Exception e) {
	  	e.printStackTrace();
	  }
    }

    @FXML
    void createAccount(ActionEvent event) {
     try{
	  Parent root = FXMLLoader.load(getClass().getResource("createAccount.fxml")); 
	  Scene scene = new Scene(root);
	  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		
	  stage.setScene(scene);
	  stage.show();
	  } catch(Exception e) {
	  	e.printStackTrace();
	  }

    }

}
