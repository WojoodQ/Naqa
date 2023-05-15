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
@Table(name="user")
public class user  implements java.io.Serializable {
    
    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="name")
    private String F_name;
    
    @Column(name="password")
    private String password;
    
    @Column(name="email")
    private String email;
    
    
    @Column(name="healthy")
    private String healthy;
    
    @Column(name="city")
    private String cob2;
    
    public user(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String F_name) {
        this.F_name = F_name;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getCob2() {
        return cob2;
    }

    public void setCob2(String cob2) {
        this.cob2 = cob2;
    }

    
}
