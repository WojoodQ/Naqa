package naqa;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
public class ProfilePageController implements Initializable {

    user us;
    /**
     * Initializes the controller class.
     */
    @FXML
    private volatile boolean stop = false;

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private Button drawerImage;

    @FXML
    private Label userName;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label mail;

    @FXML
    private Label city;

    @FXML
    private Label healthy;

    @FXML
    Boolean flag = true;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showUser(user u) {
        us = u;
        userName.setText(u.getUsername());
        name.setText(u.getF_name());
        password.setText(u.getPassword());
        city.setText(u.getCob2());
        healthy.setText(u.getHealthy());
        mail.setText(u.getEmail());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        opacityPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        drawerImage.setOnMouseClicked(event -> {

            if (flag == true) {
                opacityPane.setVisible(true);

                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), opacityPane);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(0.15);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), drawerPane);
                translateTransition1.setByX(+600);
                translateTransition1.play();
                flag = false;
            } else {
                opacityPane.setVisible(false);

                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), opacityPane);
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

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), opacityPane);
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
    private void min(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stop = true;
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
     File file;
     FileChooser fileChooser = new FileChooser();
    
             
    @FXML
    void getText(ActionEvent event) throws FileNotFoundException {
       file = fileChooser.showSaveDialog(new Stage());
    }
    

    public void saveSystem(){
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<beforeSession> list = null;
        String queryp = "from beforeSession";
        Query query = session.createQuery(queryp);
        list = query.list();
        session.getTransaction().commit();

        session.beginTransaction();
        List<afterSession> list2 = null;
        String queryp2 = "from afterSession";
        Query query2 = session.createQuery(queryp2);
        list2 = query2.list();
        session.getTransaction().commit();

        
        
        
        try {
            PrintWriter printWriter = new PrintWriter(file);
            
            printWriter.println("البيانات القبلية");
        for (beforeSession u : list) {
            if (u.getUsername().equals(us.getUsername())) {
                String temp = u.getBagsNum() + "  " + ":عدد الأكياس" + "  " + u.getBagsVol() + "  " + ":حجم الأكياس" + "  " + u.getDry() + "  " + ":جفاف" + "  " + u.getDyspnea() + "  " + ":كتمة" + "  " + u.getSwollenFeet() + "  " + ":تضخم القدمين" + "  " + u.getWeight() + "  " + ":الوزن" + "  " + u.getPulse() + "  " + ":النبض" + "  " + u.getPressure2() + "  " + ":الضغط الانبساطي" + "  " + u.getPressure1() + "  " + ":الضغط الانقباضي" + "  " + u.getDate() + "  " + ":التاريخ";
                printWriter.println(temp);
            }
        }
        

        printWriter.println("البيانات البعدية");
        for (afterSession u : list2) {
            if (u.getUsername().equals(us.getUsername())) {
                String temp = u.getLostTime() + "  " + ":معدل وقت الانتظار الضائغ " + "  " + u.getWaitingTime() + "  " + ":معدل وقت الانتظار " + "  " + u.getTotalFilterSize() + "  " + ":حجم الفلترة الاجمالي " + "  " + u.getInitialDischargeVolume() + "  " + ":حجم التصريف الابتدائي " + "  " + u.getWeight() + "  " + ":الوزن" + "  " + u.getPulse() + "  " + ":النبض" + "  " + u.getPressure2() + "  " + ":الضغط الانبساطي" + "  " + u.getPressure1() + "  " + ":الضغط الانقباضي" + "  " + u.getDate() + "  " + ":التاريخ";
                printWriter.println(temp);
            }
        }

            
        session.close();
        printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
}
