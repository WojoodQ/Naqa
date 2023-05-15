/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ThirdStepController implements Initializable {
    
    afterSession after=new afterSession();
    private Stage stage;
    private Scene scene;
    private Parent root;

   @FXML
    private Label msg;
    
    @FXML
    private JFXTextField waiting;

    @FXML
    private JFXTextField total;

    @FXML
    private JFXTextField initial;

     @FXML
    private JFXTextField pressure1;

    @FXML
    private JFXTextField pressure2;

    @FXML
    private JFXTextField pulse;

    @FXML
    private JFXTextField weight;

    
    @FXML
    private JFXTextField lostTime;

    
    user us;
    public void showUser(user u) {
        us = u;
    }
    
    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TimerStep.fxml"));
        Parent Timerroot = loader.load();
        scene = new Scene(Timerroot);
        TimerStepController TimerCon = loader.getController();
        TimerCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        if(waiting.getText().isEmpty() || total.getText().isEmpty() || initial.getText().isEmpty() ||pressure1.getText().isEmpty() || pressure2.getText().isEmpty()|| pulse.getText().isEmpty() || weight.getText().isEmpty()){
            msg.setText("من فضلك أكمل تعبئة البيانات");
        }else{
          
        after.setUsername(us.getUsername());
        after.setDate(java.time.LocalDate.now().toString());
        after.setPressure1(pressure1.getText());
        after.setPressure2(pressure2.getText());
        after.setPulse(pulse.getText());
        after.setWeight(weight.getText());
        after.setInitialDischargeVolume(initial.getText());
        after.setTotalFilterSize(total.getText());
        after.setWaitingTime(waiting.getText());
        after.setLostTime(lostTime.getText());
        session.save(after);
        session.getTransaction().commit();
        session.close();
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FinalStep.fxml"));
        Parent Finalroot = loader.load();
        scene = new Scene(Finalroot);
        FinalStepController FinalCon = loader.getController();
        FinalCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
