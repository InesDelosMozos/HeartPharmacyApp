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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Age;
import pojos.Comorbidity;
import pojos.Drug;
import pojos.Gender;
import pojos.Patient;
import pojos.Treatment;
import BITalino.BITalino;
import BITalino.BITalinoErrorTypes;
import BITalino.BITalinoException;
import BITalino.BitalinoDemo;
import BITalino.DeviceDiscoverer;
import BITalino.Frame;


public class ListPatientController implements Initializable {

    @FXML
    private TableView<Patient> patientTable = null;

    @FXML
    private TableColumn<Patient, Integer> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private Button editButton, backtoMenu, obtainTreatmentButton, recordEcgButton;

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
    private static SceneChanger sc;
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        this.editButton.setDisable(true);

        dbManager = new SQLiteManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();

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

        sc = new SceneChanger();
        Patient patient = this.patientTable.getSelectionModel().getSelectedItem(); //return the selected staff in the table
        NewPatientController npc = new NewPatientController();
        sc.changeScenesWithPatient(event, "newPatient.fxml", patient, npc);

        //sc.changeScenes(event, "newStaffController2.fxml", "cualquiera");
    }

    public void patientSelected() {
        this.editButton.setDisable(false);
    }

    public void loadPatients() {

        List<Patient> patientList = new ArrayList<Patient>();
        patientList = patientmanager.getPatients();

        //patients.addAll(patientList);
        int i;
        Patient p;
        Comorbidity comorbidity = new Comorbidity();
        Treatment treatment = new Treatment();
        ArrayList<Comorbidity> comorbidities = new ArrayList<Comorbidity>();
        ArrayList<String> stringcomorbidities = new ArrayList<String>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<String> stringtreatments = new ArrayList<String>();

        for (i = 0; i < patientList.size(); i++) {

            p = patientList.get(i);
            System.out.println(p);
            stringcomorbidities = comorbiditymanager.getComorbiditiesFromPatient(p.getId());
            p.setString_comorbidities(stringcomorbidities);
            //System.out.println(stringcomorbidities);
            stringtreatments = treatmentmanager.getTreatmentFromPatient(p.getId());
            p.setString_treatments(stringtreatments);
            for (int j = 0; j < stringcomorbidities.size(); j++) {
                comorbidity.setName(stringcomorbidities.get(j));
                comorbidities.add(comorbidity);
            }
            p.setComorbidity(comorbidities);

            for (int j = 0; j < stringtreatments.size(); j++) {
                treatment.setName(stringtreatments.get(j));
                treatments.add(treatment);
            }
            p.setTreatments(treatments);
            System.out.println(p);
            this.patients.add(p);

        }

    }

    public void backtoMenu(ActionEvent event) {
        sc = new SceneChanger();
        sc.changeScenes(event, "menu.fxml");
    }

    public void giveMedication(ActionEvent event) {

        Patient p1 = this.patientTable.getSelectionModel().getSelectedItem();
        formatearPatient(p1);
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        KieSession ksession = kc.newKieSession("diagnosisKS");
        Collection<KiePackage> kpcks = ksession.getKieBase().getKiePackages();
        for (KiePackage kpck : kpcks) {
            Collection<org.kie.api.definition.rule.Rule> krules = kpck.getRules();
            for (org.kie.api.definition.rule.Rule r : krules) {
                System.out.println(r);
            }
        }

        ksession.insert(p1);

        ksession.fireAllRules();

        System.out.println("AFTER");

        System.out.println(p1);

        ksession.dispose();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText("The medicines is: Paracetamol");//TODO mirar esto
        Optional<ButtonType> result = alert.showAndWait();
        Drug drug = new Drug();
        drug.setName(p1.getDrug());
        //int patient_id= p.getId();
        // drugmanager.add(drug);
        //int drug_id= dbManager.getLastId();
        //patientmanager.assign_drug(patient_id,drug_id);
    }

    public Patient formatearPatient(Patient p) {

        p.setAge(Age.ageFromInteger(p.getAge2()));
        p.setGender(Gender.genderFromBoolean(p.getGender2()));
        Patient p1 = new Patient(p.getHeartDisease(), p.getAge(), p.getGender(), p.getString_comorbidities(),p.getString_treatments());
        return p1;
    }
    
    

}
