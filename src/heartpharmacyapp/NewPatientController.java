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
import db.interfaces.DrugManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.sqlite.SQLiteManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.Comorbidity;
import pojos.Drug;
import pojos.Patient;
import pojos.Treatment;

/**
 *
 * @author Usuario
 */
public class NewPatientController implements Initializable, ControllerClass {

    @FXML
    private ChoiceBox<String> sexChoiceBox, diseaseChoiceBox, pregnantChoiceBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea areaComorbidity;

    @FXML
    private TextArea areaMedicines;

    @FXML
    private TextField txtname, txtage;

    @FXML
    private TableView<Comorbidity> comorbidityTable;

    @FXML
    private TableView<Treatment> medicineTable;

    @FXML
    private TableColumn<Comorbidity, String> comorbidityCol;

    @FXML
    private TableColumn<Treatment, String> medicineCol;

    @FXML
    private Button addComorbidityButton, addMedicineButton, addPatient;

    @FXML
    private TextField txtNewComorbidity, txtNewMedicine;

    private Patient selectedPatient;
    private ObservableList<Comorbidity> comorbidities = FXCollections.observableArrayList();
    private ObservableList<Treatment> medicines = FXCollections.observableArrayList();
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;

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

        comorbidityCol.setCellValueFactory(new PropertyValueFactory<Comorbidity, String>("Name"));
        medicineCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Name"));

    }

    public void addComorbidity(ActionEvent event) {

        String name = this.txtNewComorbidity.getText();
        Comorbidity c = new Comorbidity(name);
        this.comorbidities.add(c);
        //this.comorbidityTable.getItems().clear();
        this.comorbidityTable.setItems(comorbidities);
        this.txtNewComorbidity.clear();
    }

    public void addMedicine(ActionEvent event) {

        String name = this.txtNewMedicine.getText();
        Treatment m = new Treatment(name);
        this.medicines.add(m);
        //this.comorbidityTable.getItems().clear();
        this.medicineTable.setItems(medicines);
        
        this.txtNewMedicine.clear();
    }

    public void addPatient(ActionEvent event) {
        dbManager = new SQLiteManager();
        patientmanager=dbManager.getPatientManager();
        comorbiditymanager= dbManager.getComorbidityManager();
        treatmentmanager= dbManager.getTreatmentManager();
        boolean gender;
        boolean pregnant;
        String name= this.txtname.getText();
        String sex= this.sexChoiceBox.getValue();
        if(sex.equals("Male")){
            gender=false;
        }
        else{
            gender=true;
        }
        String heartdisease= this.diseaseChoiceBox.getValue();
        String stringpregnant= this.pregnantChoiceBox.getValue();
        if(stringpregnant.equals("Yes")){
            pregnant=true;
        }
        else{
            pregnant=false;
        }
        
        ArrayList<Comorbidity> comorbidity= new ArrayList<Comorbidity>();
        comorbidity.addAll(comorbidities);
        ArrayList<Treatment> drugs= new ArrayList<Treatment>();
        drugs.addAll(medicines);
        String stringage= this.txtage.getText();
        int age= Integer.parseInt(stringage);
        
        Patient p = new Patient(name,heartdisease,age,gender,pregnant);
        System.out.println(p);
        patientmanager.add(p);
        
        int patient_id= dbManager.getLastId();
        String comorb; 
        
         for(int i = 0; i<comorbidities.size(); i++){
                 comorb = comorbidities.get(i).getName();
                 System.out.println(comorb);
                 comorbiditymanager.add(comorb);
                 int comorbidity_id = dbManager.getLastId();
                 patientmanager.assign_comorbidity(patient_id, comorbidity_id);
             }
             
             String drug;
           
             for(int i = 0; i<drugs.size(); i++){
                 drug= drugs.get(i).getName();
                 treatmentmanager.add(drug);
                 int treat_id = dbManager.getLastId();
                 patientmanager.assign_treatment(patient_id, treat_id);
             }
        
        
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText("The medicines is: Paracetamol");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @Override
    public void preloadPatientData(Patient patient) {
        this.selectedPatient = patient;
        this.txtname.setText(selectedPatient.getFullName());
        Boolean pregnant = this.selectedPatient.getPregnant();
        
        if(pregnant==true){
            this.pregnantChoiceBox.setValue("Yes");
        }
        else{
            this.pregnantChoiceBox.setValue("No");
        }
       // int age= this.selectedPatient.getAge2();
        //String stringage= Integer.toString(age);
        //this.txtage.setText(stringage);
        this.diseaseChoiceBox.setValue(this.selectedPatient.getHeartDisease());
        comorbidities.addAll(selectedPatient.getComorbidity());
        medicines.addAll(selectedPatient.getTreatments());

        comorbidityTable.setItems(comorbidities);
        medicineTable.setItems(medicines);

    }
}