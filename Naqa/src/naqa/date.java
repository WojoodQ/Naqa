/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "date")
public class date {

    @Id
    @Column(name = "username")    
    private String username;
    @Column(name = "yearDate")
    private String yearDate;
    @Column(name = "monthlyDate")
    private String monthlyDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getYearDate() {
        return yearDate;
    }

    public void setYearDate(String yearDate) {
        this.yearDate = yearDate;
    }

    public String getMonthlyDate() {
        return monthlyDate;
    }

    public void setMonthlyDate(String monthlyDate) {
        this.monthlyDate = monthlyDate;
    }

    
}
