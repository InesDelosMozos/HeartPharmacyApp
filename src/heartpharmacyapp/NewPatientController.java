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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Usuario
 */
public class NewPatientController implements Initializable {

    @FXML
    private ChoiceBox<String> sexChoiceBox, diseaseChoiceBox, pregnantChoiceBox;
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // creamos el sexChoiceBox
        ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female");
        sexChoiceBox.setItems(sexList);
        sexChoiceBox.setValue("Male");

        ObservableList<String> diseaseList = FXCollections.observableArrayList("Hypertension", "Myocardial Infarction", "Angina Pectoris", "Arrythmia", "High Cholesterol", "Clots");
        diseaseChoiceBox.setItems(diseaseList);
        diseaseChoiceBox.setValue("Hypertension");

        ObservableList<String> pregnantList = FXCollections.observableArrayList("No", "Yes");
        pregnantChoiceBox.setItems(pregnantList);
        pregnantChoiceBox.setValue("No");

    }
}
