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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
public class EcgsPatientController implements ControllerClass, Initializable {

    @FXML
    private TableColumn<Ecg, Integer> idCol;

    @FXML
    private TableView<Ecg> ecgTable;

    @FXML
    private TableColumn<Ecg, String> nameCol;
     @FXML
    private Button back;
    @FXML
    private Button  showButton;
    private static Patient ecgpatient;
    private ObservableList<Ecg> ecgs = FXCollections.observableArrayList();
    private static DBManager dbManager;
    private ObservableList<Integer> dataEcg = FXCollections.observableArrayList();
    public java.util.List<Integer> indice = new ArrayList();
    NumberAxis xAxis = new NumberAxis(0,100,1);
    NumberAxis yAxis = new NumberAxis(450,550,1);
    SceneChanger sc;
    

    @FXML
    //private LineChart<String, Integer> graph;
    LineChart<Number, Number> graph = new LineChart<>(xAxis, yAxis);
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        XYChart.Series series1 = (XYChart.Series) graph.getData().get(0);
        new animatefx.animation.Pulse(series1.getNode()).play();
    }

    @Override
    public void preloadPatientData(Patient patient) {
        this.ecgpatient = patient;
        this.ecgs.addAll(this.ecgpatient.getEcgs());
        ecgTable.setItems(ecgs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        idCol.setCellValueFactory(new PropertyValueFactory<Ecg, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Ecg, String>("name_ecg"));

        // set the table editable in order to update it
        ecgTable.setEditable(true);
        this.showButton.setDisable(true);
        // load data
        //System.out.println(this.ecgpatient.getId());
        //loadEcgs();

    }
    public void activateButton(){
         this.showButton.setDisable(false);
    }

    @FXML
    void showEcg(ActionEvent event) {
        Ecg ecg = this.ecgTable.getSelectionModel().getSelectedItem();
        loadEcg(ecg);
        //XYChart.Series<String,Integer> series = new XYChart.Series<String,Integer>();
        XYChart.Series<Number, Number> series = new LineChart.Series<>();
        series.setName("Ecg");
        yAxis.setLowerBound(450);
        yAxis.setUpperBound(550);
        

        for (int i = 0; i < dataEcg.size(); i++) {
            //String indicex= indice.get(i).toString();
            //series.getData().add(new XYChart.Data<String,Integer>(indicex, dataEcg.get(i)));
            XYChart.Data<Number, Number> data = new LineChart.Data<>(i, dataEcg.get(i));
            series.getData().add(data);
             
        }

        graph.getData().add(series);
    }
    public void loadEcg(Ecg ecg) {
 
        byte[] ecg_values = ecg.getPatient_ecg();
        List<String> values = new ArrayList();
        String pasar = "";
                
        for (int i = 0; i < (ecg_values.length) - 1; i++) {
            char value = (char) ecg_values[i];
            int compare = (int) ecg_values[i];
            while (compare != 10) {
                value = (char) ecg_values[i];
                compare = (int) ecg_values[i];
                if (compare != 10) {
                    pasar = pasar + value;
                    i++;
                }
            }
            values.add(pasar);
            pasar = "";
        }
        
        for (int i = 1; i < (values.size()) - 1; i++) {
            dataEcg.add(Integer.parseInt(values.get(i)));
            
        }
        for(int j=0; j<dataEcg.size();j++ ){
            indice.add(j);
        }
            
       
    }
    @FXML
    void back(ActionEvent event) {
     sc = new SceneChanger();
     sc.changeScenes(event, "listpatient.fxml");
    }

}
