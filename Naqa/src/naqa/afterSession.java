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
@Table(name="after_session")
public class afterSession implements java.io.Serializable {
    
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
    
    
    @Column(name="weight")
    private String weight;
    
    @Column(name="initialDischargeVolume")
    private String initialDischargeVolume;
    
    @Column(name="totalFilterSize")
    private String totalFilterSize;
    
    @Column(name="waitingTime")
    private String waitingTime;
    
    @Column(name="lostTime")
    private String lostTime;
    
    public afterSession(){
        
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

    public String getLostTime() {
        return lostTime;
    }

    public void setLostTime(String lostTime) {
        this.lostTime = lostTime;
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

    public String getInitialDischargeVolume() {
        return initialDischargeVolume;
    }

    public void setInitialDischargeVolume(String initialDischargeVolume) {
        this.initialDischargeVolume = initialDischargeVolume;
    }

    public String getTotalFilterSize() {
        return totalFilterSize;
    }

    public void setTotalFilterSize(String totalFilterSize) {
        this.totalFilterSize = totalFilterSize;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    
}
