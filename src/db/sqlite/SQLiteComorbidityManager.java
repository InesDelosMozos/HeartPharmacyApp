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
    
    public SQLiteComorbidityManager(){
        super();
        
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
    
   
    
    @Override
     public ArrayList<String> getComorbiditiesFromPatient(int patientId) {
        ArrayList<String> comorbidityList = new ArrayList();
        try {
            String sql = "SELECT * FROM patients AS p JOIN patientComorbidity AS pc ON p.id = pc.patientId "
                    + "JOIN comorbidity AS c ON pc.comorbidityId=c.id "
                    + "WHERE p.id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int comorbidityId = rs.getInt(10);
                String name = rs.getString(11);
                comorbidityList.add(name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comorbidityList;
    }


    
    
}
