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

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import BITalino.BitalinoDemo;
import db.interfaces.*;
import static heartpharmacyapp.CreateAccountController.showAlert;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import static javafx.scene.control.AccordionBuilder.create;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import pojos.Ecg;
import java.security.MessageDigest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import db.sqlite.JPAUserManager;
import db.interfaces.UserManager;
import db.sqlite.SQLiteEcgManager;
import db.sqlite.SQLiteManager;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojos.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import static javafx.scene.control.AccordionBuilder.create;
import pojos.Patient;


public class ECGRecordingController implements Initializable, ControllerClass {
    
    @FXML
    private Button saveEcgButton;

    @FXML
    private TextField ecgnameButton;

    @FXML
    private Button backButtton;
    
    private static SceneChanger sc;
    
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static EcgManager ecgmanager;
    private static Patient ecgpatient;

    @FXML
    void recordEcg(ActionEvent event) throws IOException {
     String file= this.ecgnameButton.getText();
     List<Integer> ecg_list = BITalino.BitalinoDemo.main();
     recordECGMenu(file,ecg_list,this.ecgpatient.getId());
     Window owner = saveEcgButton.getScene().getWindow();
     this.saveEcgButton.setDisable(true);
     showAlert(Alert.AlertType.CONFIRMATION, owner, "ECG completed", "You can now go back to the menu");
    }

    @FXML
    void back(ActionEvent event) {
     sc = new SceneChanger();
     sc.changeScenes(event, "listpatient.fxml");
    }
    
    public static void  recordECGMenu(String file_ecg,List<Integer> arrayECG, int patient_id) throws IOException{
        dbManager = new SQLiteManager();
        ecgmanager= dbManager.getEcgManager();
        patientmanager = dbManager.getPatientManager();
        PrintWriter printWriter3 = null;
        try {
            printWriter3 = new PrintWriter(file_ecg);
            for (int i = 0; i < arrayECG.size(); i++) {
                printWriter3.print(arrayECG.get(i) + "\n");
            }
        } catch (IOException ex) {

        } finally {
            if (printWriter3 != null) {
                printWriter3.close();
            }

        }
       
        String filePath_ecg = file_ecg;

       
        byte[] patient_ecg = Files.readAllBytes(Paths.get(filePath_ecg));

       
         Ecg ecg = new Ecg(filePath_ecg, patient_ecg);
         System.out.println(ecg);
         ecgmanager.add(ecg);
         int ecg_id = dbManager.getLastId();
         patientmanager.assign_ecg(patient_id,ecg_id);
       
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    }

    @Override
    public void preloadPatientData(Patient patient) {
       
        
        this.ecgpatient = patient;
    }
             
    
}
