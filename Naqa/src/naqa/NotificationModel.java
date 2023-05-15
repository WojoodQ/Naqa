/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "notification")
public class NotificationModel implements java.io.Serializable{

    @Id @GeneratedValue
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "icon")
    private String icon;
    @Column(name = "text")
    private String text;
    @Column(name = "iconColor")
    private String iconColor;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

}
