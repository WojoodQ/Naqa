/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
public class DailySuppliesController implements Initializable {

    @FXML
    private Button edite;

    @FXML
    private Button save;

    @FXML
    private TextField sub1;

    @FXML
    private TextField sub2;

    @FXML
    private TextField sub3;

    @FXML
    private TextField sub4;

    @FXML
    private TextField sub5;

    @FXML
    private TextField sub6;

    @FXML
    private TextField sub7;
    @FXML
    private TextField sub8;
    private volatile boolean stop = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    user us;

    public void showUser(user u) {
        us = u;
    }

    @FXML
    void editing(ActionEvent e) {

        //for edit button action
        Button edit = (Button) e.getSource(); //similar to button id
        AnchorPane ap = ((AnchorPane) edit.getParent()); //button pane
        Button save = (Button) ap.getChildren().get(1);
        TextField tf = (TextField) ((Pane) ap.getParent()).getChildren().get(1);
        tf.setEditable(true);
        edit.setVisible(false);
        save.setVisible(true);
        tf.setStyle("-fx-border-color: #989898");
    }

    @FXML
    void save(ActionEvent e) {//for edit button action

        TextField tf = null;
        try {
            Button save = (Button) e.getSource(); //similar to button id
            AnchorPane ap = ((AnchorPane) save.getParent()); //button pane
            Button edit = (Button) ap.getChildren().get(0);
            tf = (TextField) ((Pane) ap.getParent()).getChildren().get(1);
            tf.setEditable(false);
            edit.setVisible(true);
            save.setVisible(false);
            tf.setStyle("-fx-border-color: transparent");
            tf.setStyle("-fx-background-color: transparent");

            Pane rectangle = (Pane) ap.getParent();
            if (tf == sub1) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set cover = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub2) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set saline = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);

            } else if (tf == sub3) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set drainBag = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub4) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set tupe = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub5) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set gauze = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub6) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set medicalMattresse = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub7) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set FaceMask = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            } else if (tf == sub8) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                String hql = "UPDATE DailySuppliesModel set clips = '" + tf.getText() + "' WHERE username = '" + us.getUsername() + "'";
                Query query = session.createQuery(hql);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
                System.out.println("Rows affected: " + result);
            }
        } catch (Exception ev) {
            tf.clear();
            tf.setPromptText("ادخل رقم");
        }
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

    public void retrieveData(user u) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hqlSelect = "from DailySuppliesModel where username='" + u.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();

        session.getTransaction().commit();
        session.close();
        DailySuppliesModel record = (DailySuppliesModel) resultsSelect.get(0);

        sub1.setText(Integer.toString(record.getCover()));
        sub2.setText(Integer.toString(record.getSaline()));
        sub3.setText(Integer.toString(record.getDrainBag()));
        sub4.setText(Integer.toString(record.getTupe()));
        sub5.setText(Integer.toString(record.getGauze()));
        sub6.setText(Integer.toString(record.getMedicalMattresse()));
        sub7.setText(Integer.toString(record.getFaceMask()));
        sub8.setText(Integer.toString(record.getClips()));
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
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
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<beforeSession> list = null;
        String queryp = "from beforeSession";
        Query query = session.createQuery(queryp);
        list = query.list();
        session.getTransaction().commit();

        output.println("البيانات القبلية");
        for (beforeSession u : list) {
            if (u.getUsername().equals(us.getUsername())) {
                String temp = u.getBagsNum() + "  " + ":عدد الأكياس" + "  " + u.getBagsVol() + "  " + ":حجم الأكياس" + "  " + u.getDry() + "  " + ":جفاف" + "  " + u.getDyspnea() + "  " + ":كتمة" + "  " + u.getSwollenFeet() + "  " + ":تضخم القدمين" + "  " + u.getWeight() + "  " + ":الوزن" + "  " + u.getPulse() + "  " + ":النبض" + "  " + u.getPressure2() + "  " + ":الضغط الانبساطي" + "  " + u.getPressure1() + "  " + ":الضغط الانقباضي" + "  " + u.getDate() + "  " + ":التاريخ";
                output.println(temp);
            }
        }
        session.beginTransaction();
        List<afterSession> list2 = null;
        String queryp2 = "from afterSession";
        Query query2 = session.createQuery(queryp2);
        list2 = query2.list();

        output.println("البيانات البعدية");
        for (afterSession u : list2) {
            if (u.getUsername().equals(us.getUsername())) {
                String temp = u.getLostTime() + "  " + ":معدل وقت الانتظار الضائغ " + "  " + u.getWaitingTime() + "  " + ":معدل وقت الانتظار " + "  " + u.getTotalFilterSize() + "  " + ":حجم الفلترة الاجمالي " + "  " + u.getInitialDischargeVolume() + "  " + ":حجم التصريف الابتدائي " + "  " + u.getWeight() + "  " + ":الوزن" + "  " + u.getPulse() + "  " + ":النبض" + "  " + u.getPressure2() + "  " + ":الضغط الانبساطي" + "  " + u.getPressure1() + "  " + ":الضغط الانقباضي" + "  " + u.getDate() + "  " + ":التاريخ";
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
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
