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
import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.sqlite.SQLiteManager;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pojos.Comorbidity;
import pojos.Drug;
import pojos.Patient;
import pojos.Treatment;

public class ListPatientController implements Initializable {

    @FXML
    private TableView<Patient> patientTable = null;

    @FXML
    private TableColumn<Patient, Integer> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private Button editButton;


    @FXML
    private TableColumn<Patient, String> heartDiseaseCol;

    @FXML
    private TextField filtro;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(patients, e -> true);
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        this.editButton.setDisable(true);
        
        dbManager = new SQLiteManager();
        patientmanager=dbManager.getPatientManager();
        comorbiditymanager= dbManager.getComorbidityManager();
        treatmentmanager= dbManager.getTreatmentManager();
        
        
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("fullName"));
        heartDiseaseCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("heartDisease"));
        
        // set the table editable in order to update it
        patientTable.setEditable(true);
        // load data
        
        loadPatients();
        patientTable.setItems(patients);
    }

    /**
     *
     * @param event
     */
    @FXML
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
            sort.comparatorProperty().bind(patientTable.comparatorProperty());
            patientTable.setItems(sort);

        });
    }

    public void editButtonPushed(ActionEvent event) {

        SceneChanger sc = new SceneChanger();
        Patient patient = this.patientTable.getSelectionModel().getSelectedItem(); //return the selected staff in the table
        NewPatientController npc = new NewPatientController();
        sc.changeScenesWithPatient(event, "newPatient.fxml", patient, npc);

        //sc.changeScenes(event, "newStaffController2.fxml", "cualquiera");
    }
    public void patientSelected(ActionEvent event){
        this.editButton.setDisable(false);
    }

    public void loadPatients() {

        List<Patient> patientList = new ArrayList<Patient>();
        patientList= patientmanager.getPatients();

        //patients.addAll(patientList);
        int i;
        Patient p;
        Comorbidity comorbidity = new Comorbidity();
        Treatment treatment = new Treatment();
         ArrayList<Comorbidity> comorbidities = new ArrayList<Comorbidity>();
         ArrayList<String> stringcomorbidities = new ArrayList<String>();
         ArrayList<Treatment> treatments = new ArrayList<Treatment>();
         ArrayList<String> stringtreatments = new ArrayList<String>();
       
       
        for(i=0;i<patientList.size();i++){
            
            p=patientList.get(i);
            System.out.println(p);
            stringcomorbidities = comorbiditymanager.getComorbiditiesFromPatient(p.getId());
            //System.out.println(stringcomorbidities);
            stringtreatments = treatmentmanager.getTreatmentFromPatient(p.getId());
             for (int j = 0; j<stringcomorbidities.size();j++){
                  comorbidity.setName(stringcomorbidities.get(j));
                  comorbidities.add(comorbidity);
             }      
            p.setComorbidity(comorbidities);
            
            for (int j = 0; j<stringtreatments.size();j++){
                 treatment.setName(stringtreatments.get(j));
                 treatments.add(treatment);
             } 
            p.setTreatments(treatments);
            System.out.println(p);
            this.patients.add(p);
            
 }

    }
}