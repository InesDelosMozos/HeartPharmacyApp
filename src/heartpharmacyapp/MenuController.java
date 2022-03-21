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
public class MenuController {
    
    @FXML
    private Button addpt;

    @FXML
    private Button ecg;

    @FXML
    private Button viewtreatment;

    @FXML
    void addPatientScreen(ActionEvent event) {
      try{
	  Parent root = FXMLLoader.load(getClass().getResource("newPatient.fxml")); 
	  Scene scene = new Scene(root);
	  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		
	  stage.setScene(scene);
	  stage.show();
	  } catch(Exception e) {
	  	e.printStackTrace();
	  }
    }

    @FXML
    void TreatmentScreen(ActionEvent event) {
 try{
	  Parent root = FXMLLoader.load(getClass().getResource("newPatient.fxml")); //cambiar fxml cuando este creado  
	  Scene scene = new Scene(root);
	  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		
	  stage.setScene(scene);
	  stage.show();
	  } catch(Exception e) {
	  	e.printStackTrace();
	  }
    }

    @FXML
    void ECGscreen(ActionEvent event) {
        try{
	  Parent root = FXMLLoader.load(getClass().getResource("login.fxml")); //cambiar fxml cuando este creado
	  Scene scene = new Scene(root);
	  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		
	  stage.setScene(scene);
	  stage.show();
	  } catch(Exception e) {
	  	e.printStackTrace();
	  }

    }

}
