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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.Ecg;
import pojos.Patient;

public class SelectEcgController implements Initializable  {

    @FXML
    private TableView<Ecg> ecgTable;

    @FXML
    private TableColumn<Ecg, String> ecgColumn;
    
    @FXML
    private Button backButton;

    @FXML
    private Button showEcgButton;

    
    private ObservableList<Ecg> ecgs = FXCollections.observableArrayList();
     private static SceneChanger sc;
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        ecgColumn.setCellValueFactory(new PropertyValueFactory<Ecg, String>("name_ecg"));
        ecgTable.setEditable(true);
        // load data

        loadEcgs();
        ecgTable.setItems(ecgs);
        
        
    }
    
    public void loadEcgs(){
        
        
        //recogemos los ecgs
    }
    
    @FXML
    void back(ActionEvent event){ 
            
        sc = new SceneChanger();
        sc.changeScenes(event, "listpatient.fxml");

    }

    @FXML
    void showEcg(ActionEvent event) {
        
        //nos vamos a otra ventana donde mostrar grafica o abrimos ventana aparte con la grafica
    
    }
}

