/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.interfaces;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import pojos.Ecg;

public interface EcgManager {

    public List<Ecg> searchByName(String name_ecg);

    public void add(Ecg ecg);

    public void delete(Integer ecg_id);
    
    public ArrayList<Ecg> getEcgFromPatient(int patientId);

}
