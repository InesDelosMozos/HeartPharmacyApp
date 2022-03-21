/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.sqlite.SQLiteManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Patient;

/**
 *
 * @author inesd
 */
public class HeartpharmacyApp extends Application {
    private static DBManager dbManager;
    private static ComorbidityManager comorbidityManager;
    private static TreatmentManager treatmentManager;
    private static PatientManager patientManager;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pantallaInicio.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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

        Patient p1 = new Patient("heart failure", true);
        List<String> comorbidity = new ArrayList<>();
        comorbidity.add("allergic to proteins ");
        p1.setComorbidity(comorbidity);
        List<String> medicines = new ArrayList<>();
        medicines.add("aspirin");
        p1.setMedicines(medicines);
        p1.setAge(Patient.Age.YOUNG);

        ksession.insert(p1);

        ksession.fireAllRules();

        System.out.println("AFTER");

        System.out.println(p1);

        ksession.dispose();
        dbManager = new SQLiteManager();
        comorbidityManager = dbManager.getComorbidityManager();
        treatmentManager = dbManager.getTreatmentManager();
        patientManager = dbManager.getPatientManager();
        
        String string = null;
        int patient_id=0;
        ComorbidityManager comorbiditymanager;

        /*String comorbidity[] = string.split(",");
        for(int i=0; i<comorbidity.length;i++){
        String symptom = comorbidity[i];
        comorbidityManager.add(symptom);
        int comorbidityId = dbManager.getLastId();
        patientManager.assign_comorbidity(patient_id, comorbidityId);
        }


        String treatment[] = string.split(",");
        for(int i=0; i<treatment.length;i++){
        String symptom = treatment[i];
        treatmentManager.add(symptom);
        int treatmentId= dbManager.getLastId();
        patientManager.assign_treatment(patient_id, treatmentId);
        }*/
    }
    
}
