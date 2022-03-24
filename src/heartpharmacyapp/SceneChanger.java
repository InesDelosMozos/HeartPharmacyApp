/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import pojos.Patient;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pojos.Ecg;

/**
 *
 * @author inesd
 */
public class SceneChanger {

    public void changeScenesWithPatient(ActionEvent event, String viewName, Patient patient,
            NewPatientController controllerClass) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);

        // access the controller class and preload
        controllerClass = loader.getController();
        controllerClass.preloadPatientData(patient);

        // get the stage from the event that was passed in
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void changeScenestoECG(ActionEvent event, String viewName, Patient patient,ECGRecordingController controllerClass) {

          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);

        // access the controller class and preload
        controllerClass = loader.getController();
        controllerClass.preloadPatientData(patient);

        // get the stage from the event that was passed in
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void changeScenes(ActionEvent event, String viewName) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void changeScenesWithEcg(ActionEvent event, String viewName, Ecg ecg, ShowEcgController controllerClass) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);
        controllerClass = loader.getController();
        //controllerClass.preloadEcgData(ecg);

        // get the stage from the event that was passed in
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
