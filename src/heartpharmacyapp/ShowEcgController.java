/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartpharmacyapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import pojos.Ecg;

/**
 *
 * @author inesd
 */
public class ShowEcgController {
    private ObservableList<Integer> dataEcg = FXCollections.observableArrayList();
    public java.util.List<Integer> indice = new ArrayList();

    @FXML
    private LineChart lineChart;

    @FXML
    private void handleButtonAction(ActionEvent event){
        XYChart.Series series1 = (XYChart.Series)lineChart.getData().get(0);
        new animatefx.animation.Pulse(series1.getNode()).play();
    }
    
    
    public void initialize(URL location, ResourceBundle resources) {  
        XYChart.Series series = new XYChart.Series();
        series.setName("Ecg");
       
            for (int i = 1; i<dataEcg.size(); i++) {
                series.getData().add(new XYChart.Data(indice.get(i),dataEcg.get(i)));
            }
       
        
        lineChart.getData().add(series);
    }
    
    public ObservableList<Integer> loadEcg(Ecg ecg) {
 
        byte[] ecg_values = ecg.getPatient_ecg();
        List<String> values = new ArrayList();
        String pasar = "";
                
        for (int i = 0; i < (ecg_values.length) - 1; i++) {
            char value = (char) ecg_values[i];
            int compare = (int) ecg_values[i];
            while (compare != 10) {
                value = (char) ecg_values[i];
                compare = (int) ecg_values[i];
                if (compare != 10) {
                    pasar = pasar + value;
                    i++;
                }
            }
            values.add(pasar);
            pasar = "";
        }
        
        for (int i = 1; i < (values.size()) - 1; i++) {
            dataEcg.add(Integer.parseInt(values.get(i)));
        }
            
        return dataEcg;
    }
}
