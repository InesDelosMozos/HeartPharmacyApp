/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author RAQUEL
 */
public enum Gender {
    MALE,FEMALE;
    
    /**
     *
     * @param gender
     * @return
     */
    public static Gender genderFromBoolean(boolean gender){
        if(gender){
            return Gender.FEMALE;
        }
        else {
            return Gender.MALE;
        }
    }
}
