/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DemoPageController implements Initializable {

    @FXML
    private MediaView mv;
    @FXML
    private Button play;
    @FXML
    private Button stop;
    @FXML 
    private AnchorPane sPane;
    
    MediaPlayer mediap;
    @FXML
    private Label currentTime;
    private volatile boolean stopPage = false;

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private Button drawerImage;

    @FXML
    private Label dateOutput;

    @FXML
    private JFXDatePicker datePickerInput;

    @FXML
    private JFXButton save;

    @FXML
    private JFXDatePicker datePickerInput2;

    @FXML
    private JFXButton save2;

    @FXML
    private Label dateOutput2;

    Boolean flag = true;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    user us;
    public void showUser(user u) {
        us = u;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String filePath="file:/C:/Users/maram/OneDrive/المستندات/NetBeansProjects/Naqa/src/video/Demo.mp4";
        Media media=new Media(filePath);
        System.out.println(filePath);
        mediap=new MediaPlayer(media);
        mv.setMediaPlayer(mediap);
        opacityPane.setVisible(false);

        javafx.animation.FadeTransition fadeTransition = new javafx.animation.FadeTransition(Duration.seconds(1), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        drawerImage.setOnMouseClicked(event -> {
            if (flag == true) {
                opacityPane.setVisible(true);

                javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(Duration.seconds(1), opacityPane);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(0.15);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), drawerPane);
                translateTransition1.setByX(+600);
                translateTransition1.play();
                flag = false;
            } else {
                opacityPane.setVisible(false);

                javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(Duration.seconds(1), opacityPane);
                fadeTransition1.setFromValue(0.15);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), drawerPane);
                translateTransition1.setByX(-600);
                translateTransition1.play();
                flag = true;
            }
        });

        opacityPane.setOnMouseClicked(event -> {

            javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(Duration.seconds(1), opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            flag = true;
        });
    }    
 
    @FXML
    void btnplay(ActionEvent event) {
        mediap.play();
    }

    @FXML
    void btnstop(ActionEvent event) {
        mediap.stop();
    }
    
    @FXML
    private void min(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stopPage = true;
        s.close();
    }

    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
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
    void stopVideo(KeyEvent event) {

        if(event.getCode()==KeyCode.HOME){
            mediap.stop();
        }
    }

    
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        java.io.File file = new java.io.File("Export to file.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(new java.io.PrintWriter(file));

        if (file.exists()) {
            System.out.println("File already exists");
        }
        Session session;
        session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<beforeSession> list =null;
        String queryp ="from beforeSession";
        Query query = session.createQuery(queryp);
        list=query.list();
        session.getTransaction().commit();
        
        output.println("البيانات القبلية");
        for(beforeSession u:list){
            if(u.getUsername().equals(us.getUsername())){
                String temp=u.getBagsNum()+"  "+":عدد الأكياس"+"  "+u.getBagsVol()+"  "+":حجم الأكياس"+"  "+u.getDry()+"  "+":جفاف"+"  "+u.getDyspnea()+"  "+":كتمة"+"  "+u.getSwollenFeet()+"  "+":تضخم القدمين"+"  "+u.getWeight()+"  "+":الوزن"+"  "+u.getPulse()+"  "+":النبض"+"  "+u.getPressure2()+"  "+":الضغط الانبساطي"+"  "+u.getPressure1()+"  "+":الضغط الانقباضي"+"  "+u.getDate()+"  "+":التاريخ";
                 output.println(temp);
            }
            }
        session.beginTransaction();
        List<afterSession> list2 =null;
        String queryp2 ="from afterSession";
        Query query2 = session.createQuery(queryp2);
        list2=query2.list();
        
        output.println("البيانات البعدية");
        for(afterSession u:list2){
            if(u.getUsername().equals(us.getUsername())){
                String temp=u.getLostTime()+"  "+":معدل وقت الانتظار الضائغ "+"  "+u.getWaitingTime()+"  "+":معدل وقت الانتظار "+"  "+u.getTotalFilterSize()+"  "+":حجم الفلترة الاجمالي "+"  "+u.getInitialDischargeVolume()+"  "+":حجم التصريف الابتدائي "+"  "+u.getWeight()+"  "+":الوزن"+"  "+u.getPulse()+"  "+":النبض"+"  "+u.getPressure2()+"  "+":الضغط الانبساطي"+"  "+u.getPressure1()+"  "+":الضغط الانقباضي"+"  "+u.getDate()+"  "+":التاريخ";
                 output.println(temp);
            }
            }
        
        session.getTransaction().commit();
        
        session.close();
        output.close();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilePage.fxml"));
        Parent Profileroot = loader.load();
        scene = new Scene(Profileroot);
        ProfilePageController ProfileCon = loader.getController();
        ProfileCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ItemPage.fxml"));
        Parent Itemroot = loader.load();
        scene = new Scene(Itemroot);
        ItemPageController ItemCon = loader.getController();
        ItemCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void switchToScene4(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DemoPage.fxml"));
        Parent Demoroot = loader.load();
        scene = new Scene(Demoroot);
        DemoPageController DemoCon = loader.getController();
        DemoCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene5(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainSign.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

}