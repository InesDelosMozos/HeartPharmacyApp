/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.sqlite;

import db.interfaces.TreatmentManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojos.Comorbidity;
import pojos.Treatment;

/**
 *
 * @author RAQUEL
 */
public class SQLiteTreatmentManager implements TreatmentManager {
    private Connection c;

    public SQLiteTreatmentManager(Connection c) {
        this.c = c;
    }
    
    public SQLiteTreatmentManager(){
        
    }
    
    public void add(String treatment) {
        try {
            String sql = "INSERT INTO treatment(name "
                    + ") "
                    + "VALUES (?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, treatment);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getTreatmentFromPatient(int patientId) {
        ArrayList<String> treatmentList = new ArrayList();
        try {
            String sql = "SELECT * FROM patients AS p JOIN patientTreatment AS pt ON p.id = pt.patientId "
                    + "JOIN treatment AS t ON pt.treatmentId=t.id "
                    + "WHERE p.id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int treatmentId = rs.getInt(10);
                String name = rs.getString(11);
                treatmentList.add(name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treatmentList;
    }
}
