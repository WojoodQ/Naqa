/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
public class HomeController implements Initializable {

    user us;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label currentTime;
    private volatile boolean stop = false;

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

    @FXML
    public VBox NotificationVbox;
    
    private boolean bellFlag = false;

    @FXML
    private AnchorPane notificationList;

    
    
    Boolean flag = true;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showUser(user u) {
        us = u;
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
        Scene HomeScene = new Scene(Homeroot);
        HomeController HomeCon = loader.getController();
        HomeCon.showUser(us);
        HomeCon.retrive(us);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void retrive(user u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(u.getUsername());
        String hqlSelect = "from date where username='" + u.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();

        tx.commit();
        session.close();
        date record = (date) resultsSelect.get(0);

        dateOutput.setText(record.getMonthlyDate().toString());
        dateOutput2.setText(record.getYearDate().toString());
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
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

    @FXML
    public void switchToScene6(ActionEvent event) throws IOException {
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
    public void switchToScene7(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Timer.fxml"));
        Parent Timerroot = loader.load();
        scene = new Scene(Timerroot);
        TimerController TimerCon = loader.getController();
        TimerCon.showUser(us);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void datePicker(ActionEvent event) {
        save.setVisible(true);
        String date = datePickerInput.getValue().toString();
        System.out.println(date);
        dateOutput.setText(date);
    }

    @FXML
    void saveDate(ActionEvent event) {
        save.setVisible(false);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "UPDATE date set  monthlyDate= '" + dateOutput.getText() + "' WHERE username = '" + us.getUsername() + "'";
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();
        tx.commit();
        session.close();
        System.out.println("Rows affected: " + result);

    }

    @FXML
    void datePicker2(ActionEvent event) {
        save2.setVisible(true);
        String date = datePickerInput2.getValue().toString();
        System.out.println(date);
        dateOutput2.setText(date);

    }

    @FXML
    void saveDate2(ActionEvent event) {
        save2.setVisible(false);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "UPDATE date set  yearDate= '" + dateOutput2.getText() + "' WHERE username = '" + us.getUsername() + "'";
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();
        tx.commit();
        session.close();
        System.out.println("Rows affected: " + result);

    }
    
     

    @FXML
    void ShowNotificationList(MouseEvent event) {
        Session session2 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session2.beginTransaction();
                String hqlSelect = "from NotificationModel where username='" + us.getUsername() + "'";
                Query querySelect = session2.createQuery(hqlSelect);
                List<NotificationModel> resultsSelect = querySelect.list();
                tx.commit();

                for (NotificationModel n : resultsSelect) {
                    NotificationVbox.getChildren().add(notificationLayout(n.getIcon(), n.getIconColor(), n.getText()));
                }
                
        if (!bellFlag) {
            notificationList.setVisible(true);
            bellFlag = true;
        } else {
            notificationList.setVisible(false);
            bellFlag = false;
        }
    }

    public HBox notificationLayout(String icon, String iconColor ,String alertMess) {
        //notification box
        HBox h = new HBox();
        h.setPadding(new Insets(10, 0, 5, 0));
        h.setPrefHeight(67);
        h.setPrefWidth(232);
        h.setAlignment(Pos.CENTER);
        h.setStyle("-fx-border-color: transparent transparent grey transparent; -fx-padding: 10 0 5 0;");

        //Left icon  
        MaterialDesignIconView notiIcon = new MaterialDesignIconView();
        notiIcon.setGlyphName(icon);
        notiIcon.setSize("28");
        notiIcon.setFill(Color.web(iconColor));

        //Right icon  
        MaterialDesignIconView delIcon = new MaterialDesignIconView();
        delIcon.setGlyphName("CLOSE");
        delIcon.setSize("17");
        delIcon.setFill(Color.RED);

        //Text
        Label l = new Label(alertMess);
        l.setMinHeight(45);
        l.setMinWidth(156);
        l.setFont(Font.font("Sakkal Majalla", 17));
        l.setTextFill(Color.web("#298898"));
        l.setWrapText(true);
        l.setTextAlignment(TextAlignment.CENTER);
        l.setAlignment(Pos.CENTER);
        l.setPadding(new Insets(-10, 0, 0, 0));
        HBox.setMargin(l, new Insets(0, 5, 0, 5));

        h.getChildren().addAll(notiIcon, l, delIcon);

        delIcon.setOnMouseClicked(e -> {
            ((VBox) h.getParent()).getChildren().remove(h);
            NotificationModel n = new NotificationModel();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            String hqlDel = "delete from NotificationModel where username='" + us.getUsername() + "' and icon='" + notiIcon.getGlyphName() + "' and text='" + l.getText() + "'";
            Query queryDel = session.createQuery(hqlDel);
            int resultD = queryDel.executeUpdate();
            System.out.println("Rows affected: " + resultD);
            tx.commit();
            session.close();
        });
        delIcon.setCursor(Cursor.HAND);
        return h;
    }


}
