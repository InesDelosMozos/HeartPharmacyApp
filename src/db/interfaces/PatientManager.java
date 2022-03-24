/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.interfaces;

import java.util.ArrayList;
import java.util.List;
import pojos.Patient;

/**
 *
 * @author RAQUEL
 */
public interface PatientManager {
    
    public void add(Patient patient);
    
    public void assign_comorbidity(int patient_id, int comorbidity_id);
    
    public void assign_treatment(int patient_id, int treatment_id);

    public void assign_drug (int patient_id, int drug_id);
    
    public Patient getPatient(int patient_id);
    
    public List<Patient> searchByName(String name);
  
    public ArrayList<Patient> getPatients();
    public void assign_ecg(int patientId, int ecgId);
    public void update(Patient patient);
}
