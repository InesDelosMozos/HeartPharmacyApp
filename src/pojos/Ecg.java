/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.sql.Date;

public class Ecg implements Serializable {

    private static final long serialVersionUID = -9008665011082537295L;
    private Integer id;
    private String name_ecg;
    private byte[] patient_ecg;

    public Ecg() {
    }

    public Ecg(String name_ecg,  byte[] patient_ecg) {
        this.name_ecg = name_ecg;  
        this.patient_ecg = patient_ecg;
    }

    public Ecg(Integer id, String name_ecg, byte[] patient_ecg) {
        this.id = id;
        this.name_ecg = name_ecg;
        this.patient_ecg = patient_ecg;
    }

    public Ecg(int id, String ecgName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_ecg() {
        return name_ecg;
    }

    public void setName_ecg(String name_ecg) {
        this.name_ecg = name_ecg;
    }

    public byte[] getPatient_ecg() {
        return patient_ecg;
    }

    public void setPatient_ecg(byte[] patient_ecg) {
        this.patient_ecg = patient_ecg;
    }

    
    
    
    /*
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name_ecg);
        hash = 67 * hash + Objects.hashCode(this.start_date);
        hash = 67 * hash + Objects.hashCode(this.finish_date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ecg other = (Ecg) obj;
        if (!Objects.equals(this.name_ecg, other.name_ecg)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.start_date, other.start_date)) {
            return false;
        }
        if (!Objects.equals(this.finish_date, other.finish_date)) {
            return false;
        }
        return true;
    }*/

    

}
