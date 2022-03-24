/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.sqlite;

import db.interfaces.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import pojos.Ecg;
//import pojos.Patient;

public class SQLiteEcgManager implements EcgManager {

    private Connection c;

    public SQLiteEcgManager(Connection c) {
        this.c = c;
    }

    @Override
    public List<Ecg> searchByName(String name_ecg) {
        List<Ecg> ecgsList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ecg WHERE name_ecg LIKE ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%" + name_ecg + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ecgName = rs.getString("name_ecg");

                Ecg newecg = new Ecg(id, ecgName);
                ecgsList.add(newecg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ecgsList;
    }

    @Override
    public void add(Ecg ecg) {
        try {
            String sql = "INSERT INTO ecg (name_ecg, ecg_array) "
                    + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, ecg.getName_ecg());
            prep.setBytes(2, ecg.getPatient_ecg());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(Integer ecg_id) {
        try {
            String sql = "DELETE FROM ecg WHERE id=?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, ecg_id);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
        }
    }
    
   public List<Ecg> getEcgFromPatient(int patientId) {
        List<Ecg> ecgsList = new ArrayList();
        try {
            String sql = "SELECT * FROM patients AS p JOIN patientEcg AS pe ON p.id = pe.patientsId "
                    + "JOIN ecg AS e ON pe.comorbidityId=e.id "
                    + "WHERE p.id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ecgName = rs.getString("name_ecg");
                byte[] ecg_array = rs.getBytes("ecg_array");
                Ecg newecg = new Ecg(id, ecgName, ecg_array);
                ecgsList.add(newecg);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ecgsList;
    }


}
