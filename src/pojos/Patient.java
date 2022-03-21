/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAQUEL
 */
public class Patient {
    
   private Integer id;
   public boolean pregnant ;
   public Gender gender;
   public String fullName;
   public Age age;
   public int age2;
   public boolean gender2;
   private String disease;
   private List<String> comorbidity = new ArrayList<>();
   private List<String> medicines = new ArrayList<>();
   //private List<String> treatments = new ArrayList<>();
   private String treatments;

    public Patient(boolean pregnant, Gender gender, String fullName, int age2, boolean gender2) {
        this.pregnant = pregnant;
        this.gender = gender;
        this.fullName = fullName;
        this.age2 = age2;
        this.gender2 = gender2;
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
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public boolean getGender2() {
        return gender2;
    }

    public void setGender2(boolean gender2) {
        this.gender2 = gender2;
    }
   
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<String> getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(List<String> comorbidity) {
        this.comorbidity = comorbidity;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
    

    
    public Patient(int newPatientId, String patientName, String patientDisease,
            Boolean patientGender, Integer patientAge, Boolean patientPregnant){
       this.id = newPatientId;
       this.fullName= patientName;
       this.disease = patientDisease;
       this.gender = Gender.genderFromBoolean(patientGender);
       this.age = Age.ageFromInteger(patientAge);
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
        this.fullName = fullName;
        this.age = Age.ageFromInteger(age);
        this.disease = disease;
    }
    
    
   
    public Patient() {
    }
    
   
    
    
}
