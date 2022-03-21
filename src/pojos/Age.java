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
public enum Age {
    
    CHILD,YOUNG,ADULT,ELDER;
    
    /**
     *
     * @param gender
     * @return
     */
    public static Age ageFromInteger(int age){
        if(age<16){
            return Age.CHILD;
        }else if(age<25){
            return Age.YOUNG;
        }else if(age<65){
            return Age.ADULT;
        }else{
            return Age.ELDER;
        }
    
    }
}
