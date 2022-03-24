/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

/**
 *
 * @author inesd
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ECGRecordingController {

    @FXML
    private Button saveEcgButton;

    @FXML
    private TextField ecgnameButton;

    @FXML
    private Button backButtton;
    
    private static SceneChanger sc;

    @FXML
    void recordEcg(ActionEvent event) {
     String file= this.ecgnameButton.getText();
     //TODO FUNCION RECORD ECG BITALINO
    }

    @FXML
    void back(ActionEvent event) {
     sc = new SceneChanger();
     sc.changeScenes(event, "listpatient.fxml");
    }

}
