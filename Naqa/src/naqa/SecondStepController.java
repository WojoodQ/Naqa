/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SecondStepController implements Initializable {

    @FXML
    private Label yellow;

    @FXML
    private Label green;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    user us;
    public void showUser(user u) {
        us = u;
    }
    public void select(user u){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hqlSelect = "from beforeSession where username='" + u.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();
        tx.commit();
        
        beforeSession record = (beforeSession) resultsSelect.get(0);
        
        
        
        int p =Integer.valueOf(record.getPressure1());
        if(record.getDry().equals("Checked") &&(p<140)){
            yellow.setText(record.getBagsNum());
            
        Transaction tx2 = session.beginTransaction();
        String hq2 = "UPDATE DailySuppliesModel set yellowBag = '" + (Double.valueOf(record.getBagsNum())) + "' WHERE username = '" + us.getUsername() + "'";
        Query query2= session.createQuery(hq2);
        query2.executeUpdate();
        tx2.commit();
        
        }
        else if(record.getDyspnea().equals("Checked") || record.getSwollenFeet().equals("Checked") && (p>140)){
            green.setText(record.getBagsNum());
            
        Transaction tx2 = session.beginTransaction();
        String hq2 = "UPDATE DailySuppliesModel set greenBag = '" + (Double.valueOf(record.getBagsNum())) + "' WHERE username = '" + us.getUsername() + "'";
        Query query2= session.createQuery(hq2);
        query2.executeUpdate();
        tx2.commit();
            
        }
        else{
            int bag=Integer.valueOf(record.getBagsNum());
            double b=bag/2.0;
            green.setText(String.valueOf(b));
            yellow.setText(String.valueOf(b));
            
        Transaction tx2 = session.beginTransaction();
        String hq2 = "UPDATE DailySuppliesModel set yellowBag = '" + b + "', greenBag = '" + b + "' WHERE username = '" + us.getUsername() + "'";
        Query query2= session.createQuery(hq2);
        query2.executeUpdate();
        tx2.commit();
        
//        Transaction tx3 = session.beginTransaction();
//        String hq3 = "UPDATE DailySuppliesModel set greenBag = '" + b + "' WHERE username = '" + us.getUsername() + "'";
//        Query query3= session.createQuery(hq3);
//        query3.executeUpdate();
//        tx3.commit();
        }
        session.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FirstStep.fxml"));
        Parent Firstroot = loader.load();
        scene = new Scene(Firstroot);
        FirstStepController FirstCon = loader.getController();
        FirstCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Video.fxml"));
        Parent Videoroot = loader.load();
        scene = new Scene(Videoroot);
        VideoController VideoCon = loader.getController();
        VideoCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
