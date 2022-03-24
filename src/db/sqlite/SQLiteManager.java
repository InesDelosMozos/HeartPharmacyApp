/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.sqlite;

import db.interfaces.* ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager implements DBManager {

    private Connection c;
    private PatientManager patient;
    private DrugManager drug;
    private ComorbidityManager comorbidity;
    private TreatmentManager treatment;
    private EcgManager ecg;
    
    public SQLiteManager() {
        super();
        this.connect();
    }

    @Override
    public void connect() {
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./db/DSS.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
           
            patient = new SQLitePatientManager(c);

            drug = new SQLiteDrugManager(c);

            comorbidity = new SQLiteComorbidityManager(c);

            treatment = new SQLiteTreatmentManager(c);
            
            ecg = new SQLiteEcgManager(c);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect() {
        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTables() {
        Statement stmt1;
        try {
            stmt1 = c.createStatement();
            String sql1 = "CREATE TABLE patients " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " fullName  TEXT   NOT NULL," + " disease   TEXT   NOT NULL," + " drug_id INTEGER  REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL, "
                    + "gender  BOOLEAN," +"age INTEGER," + "pregnant BOOLEAN)";
            stmt1.executeUpdate(sql1);
            stmt1 = c.createStatement();
            String sql2 = "CREATE TABLE drugs " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name   TEXT   NOT NULL," + "duration INTEGER NOT NULL)";
           

            stmt1.executeUpdate(sql2);
            stmt1 = c.createStatement();
            String sql3 = "CREATE TABLE patientDrug " + "(patientId     INTEGER  REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL, "
                    + "drugId     INTEGER  REFERENCES drugs(id) ON UPDATE CASCADE ON DELETE SET NULL, " + "PRIMARY KEY(patientId,drugId))";
            stmt1.executeUpdate(sql3);
            
            
            stmt1 = c.createStatement();
            String sql4 = "CREATE TABLE comorbidity " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name   TEXT   NOT NULL)";
            stmt1.executeUpdate(sql4);
            stmt1 = c.createStatement();
            String sql5 = "CREATE TABLE treatment " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name   TEXT   NOT NULL )";
            
            stmt1.executeUpdate(sql5);
            stmt1 = c.createStatement();
            String sql6 = "CREATE TABLE patientComorbidity " + "(patientId     INTEGER  REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL, "
                    + "comorbidityId     INTEGER  REFERENCES comorbidity(id) ON UPDATE CASCADE ON DELETE SET NULL, " + "PRIMARY KEY(patientId,comorbidityId))";
            
            stmt1.executeUpdate(sql6);
            stmt1 = c.createStatement();
            String sql7 = "CREATE TABLE patientTreatment " + "(patientId     INTEGER  REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL, "
                    + "treatmentId     INTEGER  REFERENCES treatment(id) ON UPDATE CASCADE ON DELETE SET NULL, " + "PRIMARY KEY(patientId,treatmentId))";
            stmt1.executeUpdate(sql7);
            stmt1 = c.createStatement();
            String sql8 = "CREATE TABLE ecg " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name_ecg   TEXT   NOT NULL,"
                    + " ecg_array BYTES)";
            stmt1.executeUpdate(sql8);
           
            stmt1 = c.createStatement();
            String sql9 = "CREATE TABLE patientEcg " + "(patientId     INTEGER  REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL, "
                    + "ecgId     INTEGER  REFERENCES ecg(id) ON UPDATE CASCADE ON DELETE SET NULL, " + "PRIMARY KEY(patientId,ecgId))";
            stmt1.executeUpdate(sql9);
            stmt1.close();
            
        } catch (SQLException e) {
            if (e.getMessage().contains("already exists")) {
            } else {
                e.printStackTrace();
            }
        }
    }
    @Override
    public EcgManager getEcgManager() {
        return ecg;
    }

    @Override
    public PatientManager getPatientManager() {
        return patient;
    }
    
    @Override
    public TreatmentManager getTreatmentManager() {
        return treatment;
    }
    
    @Override
    public ComorbidityManager getComorbidityManager() {
        return comorbidity;
    }
    
    @Override
    public DrugManager getDrugManager() {
        return drug;
    }

    public PatientManager getPatient() {
        return patient;
    }

    public void setPatient(PatientManager patient) {
        this.patient = patient;
    }

    public DrugManager getDrug() {
        return drug;
    }

    public void setDrug(DrugManager drug) {
        this.drug = drug;
    }

    public ComorbidityManager getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(ComorbidityManager comorbidity) {
        this.comorbidity = comorbidity;
    }

    public TreatmentManager getTreatment() {
        return treatment;
    }

    public void setTreatment(TreatmentManager treatment) {
        this.treatment = treatment;
    }

    public EcgManager getEcg() {
        return ecg;
    }

    public void setEcg(EcgManager ecg) {
        this.ecg = ecg;
    }

   
    @Override
    public int getLastId() {
            int result = 0;
            try {
                    String query = "SELECT last_insert_rowid() AS lastId";
                    PreparedStatement p = c.prepareStatement(query);
                    ResultSet rs = p.executeQuery();
                    result = rs.getInt("lastId");
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return result;
    }

    
}
