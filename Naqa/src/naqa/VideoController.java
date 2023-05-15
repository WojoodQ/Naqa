/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private MediaView mv;
    
    @FXML
    private JFXButton stop;

    @FXML
    private JFXButton play;
    
    MediaPlayer mediap;
    
    user us;
    public void showUser(user u) {
        us = u;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String filePath="file:/C:/Users/maram/OneDrive/المستندات/NetBeansProjects/Naqa/src/video/video.mp4";
        Media media=new Media(filePath);
        System.out.println(filePath);
        mediap=new MediaPlayer(media);
        mv.setMediaPlayer(mediap);
        mediap.play();
    }    
    
    @FXML
    void btnplay(ActionEvent event) {
        mediap.setCycleCount(MediaPlayer.INDEFINITE);
        mediap.play();
    }

    @FXML
    void btnstop(ActionEvent event) {
        mediap.stop();
    }
    
    @FXML
    void back(MouseEvent event) throws IOException {
        mediap.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SecondStep.fxml"));
        Parent Secondroot = loader.load();
        scene = new Scene(Secondroot);
        SecondStepController SecondCon = loader.getController();
        SecondCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        mediap.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TimerStep.fxml"));
        Parent Timerroot = loader.load();
        scene = new Scene(Timerroot);
        TimerStepController TimerCon = loader.getController();
        TimerCon.showUser(us);
        TimerCon.select(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
