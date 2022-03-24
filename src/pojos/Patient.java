/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author RAQUEL
 */
public class Patient {
    
   private SimpleIntegerProperty id;
   public boolean pregnant ;
   public Gender gender;
   public SimpleStringProperty fullName;
   public Age age;
   public SimpleIntegerProperty age2;
   public boolean gender2;
   private SimpleStringProperty heartdisease;
   private ArrayList<Comorbidity> comorbidity = new ArrayList<>();
   private ArrayList<Drug> drugs = new ArrayList<>();
   private ArrayList<Treatment> treatments = new ArrayList<>();
   private int drug_id;
   private ArrayList<String> string_treatments = new ArrayList<>();
   private ArrayList<String> string_comorbidities = new ArrayList<>();
   private String drug;

    
    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }
   
   
    public void setString_treatments(ArrayList<String> string_treatments) {
        this.string_treatments = string_treatments;
    }

    public void setString_comorbidities(ArrayList<String> string_comorbidities) {
        this.string_comorbidities = string_comorbidities;
    }

    public ArrayList<String> getString_treatments() {
        return string_treatments;
    }

    public ArrayList<String> getString_comorbidities() {
        return string_comorbidities;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public int getDrug_id() {
        return drug_id;
    }
   

    public Patient(String fullName,String heartdisease, int age2,boolean gender,boolean pregnant) {
        this.pregnant = pregnant;
        this.heartdisease= new SimpleStringProperty(heartdisease);
        this.fullName = new SimpleStringProperty(fullName);
        this.age2 = new SimpleIntegerProperty(age2);
        this.gender2 = gender;
    }

    public Patient(int i, String juan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public int getAge2() {
        return age2.get();
    }

    public void setAge2(int age2) {
        this.age2 = new SimpleIntegerProperty(age2);
    }

    public boolean getGender2() {
        return gender2;
    }

    public void setGender2(boolean gender2) {
        this.gender2 = gender2;
    }
   
    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName = new SimpleStringProperty(fullName);
    }
    public boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public String getHeartdisease() {
        return heartdisease.get();
    }

    public void setHeartdisease(String disease) {
        this.heartdisease = new SimpleStringProperty(disease);
    }

    public ArrayList<Comorbidity> getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(ArrayList<Comorbidity> comorbidity) {
        this.comorbidity = comorbidity;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
    }
    

    
    public Patient(Integer newPatientId, String patientName, String patientDisease,
            Boolean patientGender, Integer patientAge, Boolean patientPregnant){
       this.id = new SimpleIntegerProperty(newPatientId);
       this.fullName= new SimpleStringProperty(patientName);
       this.heartdisease = new SimpleStringProperty(patientDisease);
       this.gender = Gender.genderFromBoolean(patientGender);
       this.age2 = new SimpleIntegerProperty(patientAge);
       this.pregnant = patientPregnant;       
    }
    /*
    public enum genderEnum(patientGender){
        if(patientGender.equals(1)){
            
        }
    }*/
    public Patient(boolean pregnant, boolean gender, String fullName, int age, String disease) {
        this.pregnant = pregnant;
        this.gender = Gender.genderFromBoolean(gender);
        this.fullName = new SimpleStringProperty(fullName);
        this.age = Age.ageFromInteger(age);
        this.heartdisease = new SimpleStringProperty(disease);
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", pregnant=" + pregnant + ", fullName=" + fullName + ", heartdisease=" + heartdisease + ", comorbidity=" + comorbidity + ", treatments=" + treatments + '}';
    }

    
    
   
    public Patient() {
    }

    public Integer getId() {
      return this.id.get();
    }
    
   
    
    
}
