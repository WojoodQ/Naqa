/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author maram
 */
@Entity
@Table(name="before_session")
public class beforeSession implements java.io.Serializable {
    
    @Id
    @Column(name="username")
    private String username;
    @Id
    @Column(name="date")
    private String date;
    
    @Column(name="systolicPressure")
    private String pressure1;

    @Column(name="diastolicPressure")
    private String pressure2; 
    
    @Column(name="pulse")
    private String pulse;
    
    @Column(name="bagsNum")
    private String bagsNum;
    
    @Column(name="bagsVol")
    private String bagsVol;
    
    @Column(name="weight")
    private String weight;
    
    @Column(name="swollenFeet")
    private String swollenFeet;
    
    @Column(name="dyspnea")
    private String dyspnea;
    
    @Column(name="dry")
    private String dry;
    
    public beforeSession(){
        
    }

    public String getBagsVol() {
        return bagsVol;
    }

    public void setBagsVol(String bagsVol) {
        this.bagsVol = bagsVol;
    }
    
    public String getBagsNum() {
        return bagsNum;
    }

    public void setBagsNum(String bagsNum) {
        this.bagsNum = bagsNum;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPressure1() {
        return pressure1;
    }

    public void setPressure1(String pressure1) {
        this.pressure1 = pressure1;
    }

    public String getPressure2() {
        return pressure2;
    }

    public void setPressure2(String pressure2) {
        this.pressure2 = pressure2;
    }

    

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSwollenFeet() {
        return swollenFeet;
    }

    public void setSwollenFeet(String swollenFeet) {
        this.swollenFeet = swollenFeet;
    }

    

    public String getDyspnea() {
        return dyspnea;
    }

    public void setDyspnea(String dyspnea) {
        this.dyspnea = dyspnea;
    }

    public String getDry() {
        return dry;
    }

    public void setDry(String dry) {
        this.dry = dry;
    }
    
    
}
