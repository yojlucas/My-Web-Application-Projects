/*
CheckBoxData.java
 */
package com.mysite;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "checkBoxData")
@RequestScoped 

public class CheckBoxData implements Serializable {
    
    private String[] data = {"1", "3"};
    
    public String[] getData(){
        return data;   
    }
    public void setData(String[] data) {
        this.data = data;
    }
}
