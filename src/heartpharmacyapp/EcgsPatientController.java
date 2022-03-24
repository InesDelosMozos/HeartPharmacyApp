/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.EcgManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.sqlite.SQLiteManager;
import static heartpharmacyapp.CreateAccountController.showAlert;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import pojos.Ecg;
import pojos.Patient;

/**
 *
 * @author drijc
 */
public class EcgsPatientController {
    
    @FXML
    private TableView<Ecg> ecgTable = null;

    @FXML
    private TableColumn<Ecg, Integer> idCol;
    @FXML
    private TableColumn<Ecg, String> nameCol;
    @FXML
    private Button backButton;
    @FXML
    private Button showEcgButton;

    @FXML
    private TableColumn<Patient, String> heartDiseaseCol;
    private static Patient ecgpatient;
 
    
    private ObservableList<Ecg> ecgs = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(ecgs, e -> true);
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;
    private static EcgManager ecgmanager;
    private static SceneChanger sc;

    @FXML
    void recordEcg(ActionEvent event) throws IOException {
  
    }

    @FXML
    void back(ActionEvent event) {
     sc = new SceneChanger();
     sc.changeScenes(event, "listpatient.fxml");
    }
    
    @FXML
    private TextField filtro;

   
   
    public void initialize(URL location, ResourceBundle resources) {

        dbManager = new SQLiteManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();

        idCol.setCellValueFactory(new PropertyValueFactory<Ecg, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Ecg, String>("ecg_name"));

        // set the table editable in order to update it
        ecgTable.setEditable(true);
        // load data

        loadEcgs();
       
        ecgTable.setItems(ecgs);
    }
    
    public void loadEcgs() {

        List<Ecg> ecgList = new ArrayList<Ecg>();
        ecgList = ecgmanager.getEcgFromPatient(this.ecgpatient.getId());

        int i;
        Ecg ecg;

        for (i = 0; i < ecgList.size(); i++) {

            ecg = ecgList.get(i);
            this.ecgs.add(ecg);

        }

    }
    public void search(KeyEvent event) {

        filtro.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super Patient>) (Patient mp) -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (mp.getFullName().contains(newValue)) {
                    return true;
                }

                return false;
            });

            SortedList sort = new SortedList(filter);
            sort.comparatorProperty().bind(ecgTable.comparatorProperty());
            ecgTable.setItems(sort);

        });
    }
    public void showEcg(ActionEvent event) {

        sc = new SceneChanger();
        Ecg ecg = this.ecgTable.getSelectionModel().getSelectedItem(); //return the selected staff in the table
        ShowEcgController ecgcontrol = new ShowEcgController();
        sc.changeScenesWithEcg(event,"showEcg.fxml", ecg,ecgcontrol);

        //sc.changeScenes(event, "newStaffController2.fxml", "cualquiera");
    }
    public static void  recordECGMenu(String file_ecg,List<Integer> arrayECG, int patient_id) throws IOException{
       
       
    }
             
    
}
