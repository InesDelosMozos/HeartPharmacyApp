/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.sqlite;

import db.interfaces.PatientManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojos.Patient;

/**
 *
 * @author RAQUEL
 */
public class SQLitePatientManager implements PatientManager {

    private Connection c;

    public SQLitePatientManager(Connection c) {
        this.c = c;
    }

    public SQLitePatientManager() {
        super();
    }

    public void add(Patient patient) {
        try {
            String sql = "INSERT INTO patients (fullName,disease, age, gender, pregnant "
                    + ") "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, patient.getFullName());
            prep.setString(2, patient.getHeartdisease());
            prep.setInt(3, patient.getAge2());
            prep.setBoolean(4, patient.getGender2());
            prep.setBoolean(5, patient.getPregnant());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assign_drug(int patientId, int drugId) {
        // Link Product and Component
        try {
            String sql = "INSERT INTO patientDrug (patientId,drugId) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, drugId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assign_comorbidity(int patientId, int comorbidityId) {
        // Link Product and Component
        try {
            String sql = "INSERT INTO patientComorbidity (patientId,comorbidityId) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, comorbidityId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assign_treatment(int patientId, int treatmentId) {
        // Link Product and Component
        try {
            String sql = "INSERT INTO patientTreatment (patientId,treatmentId) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, treatmentId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> patientsList = new ArrayList<Patient>();
        try {
            String sql = "SELECT * FROM patients";
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String patientName = rs.getString("fullName");
                String patientDisease = rs.getString("disease");
                Integer patientDrug = rs.getInt("drug_id");
                Boolean patientGender = rs.getBoolean("gender");
                Integer patientAge = rs.getInt("age");
                Boolean patientPregnant = rs.getBoolean("pregnant");

                Patient newpatient = new Patient(id, patientName, patientDisease,
                        patientGender, patientAge, patientPregnant);
                System.out.println(patientAge);
                patientsList.add(newpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientsList;
    }

    public Patient getPatient(int patientId) {
        Patient newPatient = null;
        try {
            String sql = "SELECT * FROM patients" + " WHERE id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            boolean patientCreated = false;
            while (rs.next()) {
                if (!patientCreated) {
                    int newPatientId = rs.getInt(1);
                    String patientName = rs.getString(2);
                    String patientDisease = rs.getString(3);
                    Integer patientDrug = rs.getInt(4);
                    Boolean patientGender = rs.getBoolean(5);
                    Integer patientAge = rs.getInt(6);
                    Boolean patientPregnant = rs.getBoolean(7);

                    newPatient = new Patient(newPatientId, patientName, patientDisease,
                            patientGender, patientAge, patientPregnant);

                    patientCreated = true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPatient;
    }

    @Override
    public List<Patient> searchByName(String name) {
        List<Patient> patientsList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM patients WHERE fullname LIKE ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%" + name + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String patientName = rs.getString("fullName");
                String patientDisease = rs.getString("disease");
                Integer patientDrug = rs.getInt("drug_id");
                Boolean patientGender = rs.getBoolean("gender");
                Integer patientAge = rs.getInt("age");
                Boolean patientPregnant = rs.getBoolean("pregnant");

                Patient newpatient = new Patient(id, patientName, patientDisease,
                        patientGender, patientAge, patientPregnant);
                patientsList.add(newpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientsList;
    }

    public void assign_ecg(int patientId, int ecgId) {
        // Link Product and Component
        try {
            String sql = "INSERT INTO patientEcg (patientId,ecgId) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, ecgId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Patient patient) {
        try {
// Update the number of products
            String sql = "UPDATE patients SET drug_id=? WHERE id=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(2, patient.getId());
            s.setInt(1, patient.getDrug_id());
            s.executeUpdate();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
