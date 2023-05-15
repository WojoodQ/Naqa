/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TimerController implements Initializable {

    /**
     * Initializes the controller class.
     */
public static Stage stage = null;
private Scene scene;
    private Parent root;
    
     @FXML
    private AnchorPane timerPane;

    @FXML
    private Text hourTimer;

    @FXML
    private Text minTimer;

    @FXML
    private Text secTimer;

    @FXML
    private JFXButton stopButton;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private JFXComboBox<Integer> hourInput;

    @FXML
    private JFXComboBox<Integer> minInput;

    @FXML
    private JFXComboBox<Integer> secInput;

    @FXML
    private JFXButton startButton;
    
    Map<Integer,String> numberMap;
    Integer currSeconds;
    Thread thrd;
    String path = "C:\\Users\\maram\\OneDrive\\المستندات\\NetBeansProjects\\Naqa\\src\\sound\\alarm.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    
    user us;
    public void showUser(user u) {
        us = u;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> hourList = FXCollections.observableArrayList();
        ObservableList<Integer> minAndsecList = FXCollections.observableArrayList();
        for(int i =0; i<=60;i++){
            if(0<=i && i<=24){
                hourList.add(new Integer(i));
            }
            minAndsecList.add(new Integer(i));
        }
        hourInput.setItems(hourList);
        hourInput.setValue(0);
        
        minInput.setItems(minAndsecList);
        minInput.setValue(0);

        secInput.setItems(minAndsecList);
        secInput.setValue(0);
        
        numberMap = new TreeMap<Integer, String>();
        for(Integer i = 0; i<= 60; i++){
            if(0<=i && i<= 9){
                numberMap.put(i, "0"+ i.toString());
            } else {
                numberMap.put(i, i.toString());
            }
        }
    }    
    @FXML
    private void close(MouseEvent event) throws IOException {
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
    private void min(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }
    
    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        thrd.stop();
        mediaPlayer.stop();
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
    void start(ActionEvent event) {
        currSeconds = hmsToSeconds(hourInput.getValue(), minInput.getValue(), secInput.getValue());
        hourInput.setValue(0);
        minInput.setValue(0);
        secInput.setValue(0);
        scrollUp();
    }

    void startCountdown(){
        thrd = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(true){
                        setOutput(); 
                        Thread.sleep(1000);
                        if(currSeconds==0){
                            System.out.println("Finshed");
                            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                            mediaPlayer.play();
                            thrd.stop();
                        }
                        currSeconds -=1;
                    }
                } catch (Exception e){
                    
                }
            }
        });
        thrd.start();
    }
    
    void setOutput(){
        LinkedList<Integer> currHms = secondsToHms(currSeconds);
        hourTimer.setText(numberMap.get(currHms.get(0)));
        minTimer.setText(numberMap.get(currHms.get(1)));
        secTimer.setText(numberMap.get(currHms.get(2)));
    }
    
    @FXML
    void stop(ActionEvent event) {
        thrd.stop();
        mediaPlayer.stop();
        scrollDown();
    }
    
    Integer hmsToSeconds(Integer h, Integer m, Integer s){
        Integer hToSeconds = h * 3600;
        Integer mToSeconds = m * 60;
        Integer total = hToSeconds + mToSeconds + s;
        return total;
    }
    
    LinkedList<Integer> secondsToHms(Integer currSecond){
        Integer hours = currSecond / 3600;
        currSecond= currSecond % 3600;
        Integer minutes = currSecond / 60;
        currSecond= currSecond % 60;
        Integer seconds = currSecond;
        LinkedList<Integer> answer = new LinkedList<>();
        answer.add(hours);
        answer.add(minutes);
        answer.add(seconds);
        return answer;
    }
    
    void scrollDown(){
        TranslateTransition tr1 = new TranslateTransition();
        tr1.setDuration(Duration.millis(300));
        tr1.setToX(0);
        tr1.setToY(800);
        tr1.setNode(timerPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(200));
        tr2.setFromX(0);
        tr2.setFromY(-800);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(menuPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2);
        pt.play();
    }
    
    void scrollUp(){
        TranslateTransition tr1 = new TranslateTransition();
        tr1.setDuration(Duration.millis(300));
        tr1.setToX(0);
        tr1.setToY(-800);
        tr1.setNode(menuPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(200));
        tr2.setFromX(0);
        tr2.setFromY(800);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(timerPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2);
        pt.setOnFinished(e ->{
            try{
                System.out.println("Start Countdown...");
                startCountdown();
            }catch(Exception e2){
                
            }
        });
        pt.play();
    }
}
