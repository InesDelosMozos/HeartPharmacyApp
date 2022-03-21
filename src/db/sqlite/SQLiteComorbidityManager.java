/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.sqlite;

import db.interfaces.ComorbidityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojos.Comorbidity;
import pojos.Patient;

/**
 *
 * @author RAQUEL
 */

public class SQLiteComorbidityManager implements ComorbidityManager {
    private Connection c;

    public SQLiteComorbidityManager(Connection c) {
        this.c = c;
    }
    
    public void add(String comorbidity) {
        try {
            String sql = "INSERT INTO comorbidity (name "
                    + ") "
                    + "VALUES (?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, comorbidity);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Comorbidity> getComorbiditiesFromPatient(int patientId) {
        List<Comorbidity> comorbidityList = new ArrayList();
        try {
            String sql = "SELECT * FROM doctors AS d JOIN doctorPatients AS dp ON d.id = dp.doctorId "
                    + "JOIN patients AS p ON dp.patientId=p.id "
                    + "WHERE d.id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, doctorId);
            ResultSet rs = p.executeQuery();
            boolean productCreated = false;
            while (rs.next()) {
                int patientId = rs.getInt(6);
                String full_name = rs.getString(7);
                int age = rs.getInt(8);
                Float weight = rs.getFloat(9);
                Float height = rs.getFloat(10);
                String gender = rs.getString(11);
                String nameuser = rs.getString(12);
                Patient newPatient = new Patient(patientId, full_name, age, weight, height, gender, nameuser);
                patientList.add(newPatient);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comorbidityList;
    }

    
    
}
