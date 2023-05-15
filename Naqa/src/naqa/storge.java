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
@Table(name="storge")
public class storge implements java.io.Serializable {
    
    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="greenBag")
    private int greenBag;
    
    @Column(name="yellowBag")
    private int yellowBag;
    
    @Column(name="purpleBag")
    private int purbleBag;
    
    @Column(name="drainBag")
    private int drainBag;
    
    @Column(name="tube")
    private int tube;
    
    @Column(name="gauze")
    private int gauze;
    
    @Column(name="medicalMattresse")
    private int medicalMattresse;
    
    @Column(name="sterilizer")
    private int sterilizer;
    
    @Column(name="tapes")
    private int tapes;
    
    @Column(name="faceMask")
    private int faceMasks;
    
    @Column(name="clips")
    private int clips;


    @Column(name="cover")
    private int cover;
    
    @Column(name="Saline")
    private int Saline;
    
    @Column(name="ointment")
    private int ointment;
    
    public storge(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGreenBag() {
        return greenBag;
    }

    public void setGreenBag(int greenBag) {
        this.greenBag = greenBag;
    }

    public int getYellowBag() {
        return yellowBag;
    }

    public void setYellowBag(int yellowBag) {
        this.yellowBag = yellowBag;
    }

    public int getPurbleBag() {
        return purbleBag;
    }

    public void setPurbleBag(int purbleBag) {
        this.purbleBag = purbleBag;
    }

    public int getDrainBag() {
        return drainBag;
    }

    public void setDrainBag(int drainBag) {
        this.drainBag = drainBag;
    }

    public int getTube() {
        return tube;
    }

    public void setTube(int tube) {
        this.tube = tube;
    }

    public int getGauze() {
        return gauze;
    }

    public void setGauze(int gauze) {
        this.gauze = gauze;
    }

    public int getMedicalMattresse() {
        return medicalMattresse;
    }

    public void setMedicalMattresse(int medicalMattresse) {
        this.medicalMattresse = medicalMattresse;
    }

    public int getSterilizer() {
        return sterilizer;
    }

    public void setSterilizer(int sterilizer) {
        this.sterilizer = sterilizer;
    }

    public int getTapes() {
        return tapes;
    }

    public void setTapes(int tapes) {
        this.tapes = tapes;
    }

    public int getFaceMasks() {
        return faceMasks;
    }

    public void setFaceMasks(int faceMasks) {
        this.faceMasks = faceMasks;
    }

    public int getClips() {
        return clips;
    }

    public void setClips(int clips) {
        this.clips = clips;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int getSaline() {
        return Saline;
    }

    public void setSaline(int Saline) {
        this.Saline = Saline;
    }

    public int getOintment() {
        return ointment;
    }

    public void setOintment(int ointment) {
        this.ointment = ointment;
    }
    
    
}
