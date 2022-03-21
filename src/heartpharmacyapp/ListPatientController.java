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
import java.util.*;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.Patient;
import javafx.scene.input.KeyEvent;

public class ListPatientController implements Initializable {

    @FXML
    private TableView<Patient> patientTable = null;

    @FXML
    private TableColumn<Patient, Integer> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TextField filtro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(patients, e -> true);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
/*
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("Name"));
        // set the table editable in order to update it
        patientTable.setEditable(true);
        // load data
        List<Patient> patientList = new ArrayList<Patient>();
        //pacientList = DBConnection.listPacients();
        Patient p = new Patient(1, "Juan");
        Patient p2 = new Patient(2, "Jorge");

        patientList.add(p);
        patientList.add(p2);
        patients.addAll(patientList);

        patientTable.setItems(patients);
    }
*/
    /**
     *
     * @param event
     */
    /*
    @FXML
    public void search(KeyEvent event) {

        filtro.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super Patient>) (Patient mp) -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (mp.getName().contains(newValue)) {
                    return true;
                }

                return false;
            });

            SortedList sort = new SortedList(filter);
            sort.comparatorProperty().bind(patientTable.comparatorProperty());
            patientTable.setItems(sort);

        });
    }

    public ObservableList<Patient> loadPatients() {

        List<Patient> patientList = null;
        //pacientList = DBConnection.listPacients();

        Patient p = new Patient(1, "Juan");
        patientList.add(p);
        patients.addAll(patientList);

        return patients;

    }*/
    
    }