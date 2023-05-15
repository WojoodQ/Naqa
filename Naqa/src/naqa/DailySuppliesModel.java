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
 * @author hp
 */
@Entity
@Table(name = "daily_consumption")
public class DailySuppliesModel {
    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name="drainBag")
    private int drainBag;
    @Column(name="tupe")
    private int tupe;
    @Column(name="gauze")
    private int gauze;
    @Column(name="medicalMattresse")
    private int medicalMattresse;
    @Column(name="FaceMask")
    private int FaceMask;
    @Column(name="clips")
    private int clips;
    @Column(name="cover")
    private int cover;
    @Column(name="saline")
    private int saline;
    
    @Column(name="yellowBag")
    private double yellowBag;
    @Column(name="greenBag")
    private double greenBag;

    public int getTupe() {
        return tupe;
    }

    public void setTupe(int tupe) {
        this.tupe = tupe;
    }

    public double getYellowBag() {
        return yellowBag;
    }

    public void setYellowBag(double yellowBag) {
        this.yellowBag = yellowBag;
    }

    public double getGreenBag() {
        return greenBag;
    }

    public void setGreenBag(double greenBag) {
        this.greenBag = greenBag;
    }

    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setDrainBag(int drainBag) {
        this.drainBag = drainBag;
    }


    public void setGauze(int gauze) {
        this.gauze = gauze;
    }

    public void setMedicalMattresse(int medicalMattresse) {
        this.medicalMattresse = medicalMattresse;
    }

    public void setFaceMask(int FaceMask) {
        this.FaceMask = FaceMask;
    }

    public void setClips(int clips) {
        this.clips = clips;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public void setSaline(int saline) {
        this.saline = saline;
    }

    public String getUsername() {
        return username;
    }

    public int getDrainBag() {
        return drainBag;
    }


    public int getGauze() {
        return gauze;
    }

    public int getMedicalMattresse() {
        return medicalMattresse;
    }

    public int getFaceMask() {
        return FaceMask;
    }

    public int getClips() {
        return clips;
    }

    public int getCover() {
        return cover;
    }

    public int getSaline() {
        return saline;
    }
    
    
    
}
