/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author RAQUEL
 */
public class Drug {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty duration;

    public Drug() {
    }

    public Drug(Integer id, String name, Integer duration) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.duration = new SimpleIntegerProperty(duration);
    }

    public Drug(String name) {
       this.name = new SimpleStringProperty(name);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Integer getDuration() {
        return duration.get();
    }

    public void setDuration(Integer duration) {
        this.duration = new SimpleIntegerProperty(duration);
    }
    
}
