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
import java.util.List;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TimerStepController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private volatile boolean stop = false;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
     @FXML
    private AnchorPane timerPane;

    @FXML
    private Text hourTimer;

    @FXML
    private Text minTimer;

    @FXML
    private JFXButton resetButton;
    
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
    
     public void select(user u){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hqlSelect = "from beforeSession where username='" + u.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();
        
        tx.commit();
        session.close();
        beforeSession record = (beforeSession) resultsSelect.get(0);
        
        
        if(record.getBagsVol().equals("2500")){
            int s=Integer.valueOf(record.getBagsNum());
            ObservableList<Integer> hourList = FXCollections.observableArrayList(s);
            hourInput.setItems(hourList);
            hourInput.setValue(s);
        }
        else{
            int s=Integer.valueOf(record.getBagsNum())*2;
            ObservableList<Integer> hourList = FXCollections.observableArrayList(s);
            hourInput.setItems(hourList);
            hourInput.setValue(s);
        }
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> minAndsecList = FXCollections.observableArrayList(0);

        
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
        loader.setLocation(getClass().getResource("Video.fxml"));
        Parent Videoroot = loader.load();
        scene = new Scene(Videoroot);
        VideoController VideoCon = loader.getController();
        VideoCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        thrd.stop();
        mediaPlayer.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ThirdStep.fxml"));
        Parent Thirdroot = loader.load();
        scene = new Scene(Thirdroot);
        ThirdStepController ThirdCon = loader.getController();
        ThirdCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void start(ActionEvent event) {
        currSeconds = hmsToSeconds(hourInput.getValue(), minInput.getValue(), secInput.getValue());
        hourInput.setValue(6);
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
    void reset(ActionEvent event) {
        thrd.resume();
        mediaPlayer.stop();
//        scrollDown();
    }
    
    @FXML
    void stop(ActionEvent event) {
        thrd.suspend();
        mediaPlayer.stop();
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

