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
import javafx.event.ActionEvent;
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

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ItemPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label msg;

    user us;
    public void showUser(user u) {
        us = u;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent Homeroot = loader.load();
        scene = new Scene(Homeroot);
        HomeController HomeCon = loader.getController();
        HomeCon.showUser(us);
        HomeCon.retrive(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void charPage(ActionEvent event) throws IOException {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<monthlyconsumption> list =null;
        String queryp ="from monthlyconsumption";
        Query query = session.createQuery(queryp);
        list=query.list();
        session.getTransaction().commit();
        session.close();
        int days=0;
        for(monthlyconsumption u:list){
            if(u.getUsername().equals(us.getUsername())){
                String datestr=u.getDate();
                String[] Date = datestr.split("-");
                String d=java.time.LocalDate.now().toString();
                String[] Date2 = d.split("-");
                if(Date[1].equals((Date2[1]))){
                    days++;
                }}}
                    
        if(days==0){
            msg.setText("ليس لديك بيانات إستهلاك شهري لعرضها");
        }else{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CharPage.fxml"));
        Parent Charroot = loader.load();
        scene = new Scene(Charroot);
        CharPageController CharCon = loader.getController();
        CharCon.us=us;
        if(CharCon.us.equals(us))
            System.out.println("KOKO");
        CharCon.showUser(us);
       
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
    }

    @FXML
    void dailyConumption(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DailySupplies.fxml"));
        Parent Dailyroot = loader.load();
        scene = new Scene(Dailyroot);
        DailySuppliesController DailyCon = loader.getController();
        DailyCon.showUser(us);
        DailyCon.retrieveData(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void monthlyConumption(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MonthlySupplies.fxml"));
        Parent Monthlyroot = loader.load();
        scene = new Scene(Monthlyroot);
        MonthlySuppliesController MonthlyCon = loader.getController();
        MonthlyCon.showUser(us);
        MonthlyCon.retrieveData(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void stores(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Map.fxml"));
        Parent Maproot = loader.load();
        scene = new Scene(Maproot);
        MapController MapCon = loader.getController();
        MapCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
