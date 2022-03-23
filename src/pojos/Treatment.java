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
public class Treatment {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public Treatment(String medicine) {
       this.name= new SimpleStringProperty(medicine);
    }

    public Integer getId() {
        return id.get();
    }

    @Override
    public String toString() {
        return "Treatment{" + "name=" + name + '}';
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

    public Treatment(Integer id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Treatment() {
    }
    
}
