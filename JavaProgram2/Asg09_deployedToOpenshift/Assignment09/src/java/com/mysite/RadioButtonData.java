/*
RadioButtonData.java
 */
package com.mysite;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
        
        @Named(value = "radioButtonData")
        @RequestScoped 
        
public class RadioButtonData implements Serializable {
            
            private String data = "2";
            
            public String getData() {
                return data;   
            }
            
            public void setData(String data){
                this.data = data;
            }   
}
