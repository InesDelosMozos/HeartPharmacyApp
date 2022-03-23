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
public class Comorbidity {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public Comorbidity(Integer id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Comorbidity(String comorbidities) {
        this.name = new SimpleStringProperty(comorbidities);
       
    }
    public Comorbidity(){
        super();
    }

    @Override
    public String toString() {
        return "Comorbidity{" + "name=" + name + '}';
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
    
}
