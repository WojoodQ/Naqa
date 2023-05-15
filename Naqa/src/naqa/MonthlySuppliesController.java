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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hp
 */
public class MonthlySuppliesController extends Thread implements Initializable {

    @FXML
    private TextField t1;

    @FXML
    private ToggleButton buttong;

    @FXML
    private ToggleButton buttont;

    @FXML
    private BorderPane root;

    @FXML
    private AnchorPane card1;
    @FXML
    private Label lbcard1;
    @FXML
    private TextField tfcard1;

    @FXML
    private AnchorPane card2;
    @FXML
    private Label lbcard2;
    @FXML
    private TextField tfcard2;

    @FXML
    private AnchorPane card3;
    @FXML
    private Label lbcard3;
    @FXML
    private TextField tfcard3;

    @FXML
    private AnchorPane card4;
    @FXML
    private Label lbcard4;
    @FXML
    private TextField tfcard4;

    @FXML
    private AnchorPane card5;
    @FXML
    private Label lbcard5;
    @FXML
    private TextField tfcard5;

    @FXML
    private AnchorPane card6;
    @FXML
    private Label lbcard6;
    @FXML
    private TextField tfcard6;

    @FXML
    private AnchorPane card7;
    @FXML
    private Label lbcard7;
    @FXML
    private TextField tfcard7;

    @FXML
    private AnchorPane card8;
    @FXML
    private Label lbcard8;
    @FXML
    private TextField tfcard8;

    @FXML
    private AnchorPane card9;
    @FXML
    private Label lbcard9;
    @FXML
    private TextField tfcard9;

    @FXML
    private AnchorPane card10;
    @FXML
    private Label lbcard10;
    @FXML
    private TextField tfcard10;

    @FXML
    private AnchorPane card11;
    @FXML
    private Label lbcard11;
    @FXML
    private TextField tfcard11;

    @FXML
    private AnchorPane card12;
    @FXML
    private Label lbcard12;
    @FXML
    private TextField tfcard12;

    @FXML
    private AnchorPane card13;
    @FXML
    private Label lbcard13;
    @FXML
    private TextField tfcard13;

    @FXML
    private AnchorPane card14;
    @FXML
    private Label lbcard14;
    @FXML
    private TextField tfcard14;

    @FXML
    private AnchorPane card15;
    @FXML
    private Label lbcard15;
    @FXML
    private TextField tfcard15;

    @FXML
    private AnchorPane card16;
    @FXML
    private Label lbcard16;
    @FXML

    private TextField tfcard16;

////////////////////////////////////////////////////////////////////////////////    
    private AnchorPane cardo;

    VBox vbfront;
    VBox vbback;
    private ScaleTransition front;
    private ScaleTransition back;

    private volatile boolean stop = false;
    Boolean flag1 = true;
    private Stage stage;
    private Scene scene;
    private Parent root1;

    @FXML
    private Label currentTime;

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private Button drawerImage;

    user us = new user();
    storge storge = new storge();

    String name = "";

    public void showUser(user u) {
        us = u;
        name = u.getUsername();
    }

    @FXML
    void handle(MouseEvent e) {
        cardo = (AnchorPane) e.getSource();
        Label flag = (Label) cardo.getChildren().get(2);
        if (Integer.valueOf(flag.getText()) == 1) {

            vbfront = (VBox) cardo.getChildren().get(1);
            vbback = (VBox) cardo.getChildren().get(0);

            front = new ScaleTransition(Duration.millis(50), vbfront);

            back = new ScaleTransition(Duration.millis(50), vbback);

            front.setFromX(1);
            front.setToX(0);

            vbback.setScaleX(0);

            back.setFromX(0);
            back.setToX(1);

            front.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    back.play();

                }

            });
            front.play();

            flag.setText("0");

        } else {

            vbfront = (VBox) cardo.getChildren().get(1);
            vbback = (VBox) cardo.getChildren().get(0);

            front = new ScaleTransition(Duration.millis(50), vbfront);

            back = new ScaleTransition(Duration.millis(50), vbback);

            front.setFromX(0);
            front.setToX(1);

            vbfront.setScaleX(0);

            back.setFromX(1);
            back.setToX(0);
            back.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    front.play();
                }

            });

            back.play();

            flag.setText("1");

        }

    }

    @FXML
    void increaseQuantity(ActionEvent e) {
        Button pressedButton = (Button) e.getSource();
        TextField quantity = (TextField) ((HBox) pressedButton.getParent()).getChildren().get(1);
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");// 1. get text from text field/ 2. convert it to int/ 3. increase it by 1/ 4. convert it back to string/5. set it to text field

    }

    @FXML
    void decreaseQuantity(ActionEvent e) {
        Node pressedButton = (Node) e.getSource();
        TextField tfquantity = (TextField) ((HBox) pressedButton.getParent()).getChildren().get(1);
        int q = Integer.valueOf(tfquantity.getText());
        if (q > 0) {
            tfquantity.setText((q - 1) + "");// 1. get text from text field/ 2. convert it to int/ 3. decrease it by 1/ 4. convert it back to string/5. set it to text field
        }
    }

    @FXML
    void save(ActionEvent e) {
        TextField quantity = null;
        try {
            Button pressedButton = (Button) e.getSource();
            quantity = (TextField) ((HBox) ((VBox) pressedButton.getParent()).getChildren().get(1)).getChildren().get(1);
            Label frontQuantity = (Label) ((VBox) ((AnchorPane) pressedButton.getParent().getParent()).getChildren().get(1)).getChildren().get(2);
            if (Integer.valueOf(quantity.getText()) >= 0) {
                frontQuantity.setText(quantity.getText());
            }
            cardo = (AnchorPane) ((VBox) ((Button) e.getSource()).getParent()).getParent();

            // flip the card after saving the chnges
            vbfront = (VBox) cardo.getChildren().get(1);
            vbback = (VBox) cardo.getChildren().get(0);

            front = new ScaleTransition(Duration.millis(50), vbfront);

            back = new ScaleTransition(Duration.millis(50), vbback);

            front.setFromX(0);
            front.setToX(1);

            vbfront.setScaleX(0);

            back.setFromX(1);
            back.setToX(0);
            back.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    front.play();
                }
            });

            back.play();
            ((Label) cardo.getChildren().get(2)).setText("1");
            checkQ(cardo, quantity, us.getUsername());
            if (Integer.valueOf(quantity.getText()) >= 0) {
                update(cardo, quantity.getText(), us.getUsername());
            }
        } catch (Exception ev) {
            quantity.clear();
            quantity.setPromptText("ادخل رقم");
        }

    }

    public void update(AnchorPane card, String quantity, String username) {
        if (card == card1) { //كمامات
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            String hql = "UPDATE storge set faceMask = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);

        } else if (card == card3) { // المعقم
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set sterilizer = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card4) { // المفارش
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set medicalMattresse = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card5) { //الكلبسات
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set clips = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card6) {// شاش
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set gauze = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card7) {// غطا
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set cover = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card8) {// محلول
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set saline = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card9) {// مرهم 
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set ointment = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card10) {// لاصق
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set tapes = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card11) {// بنفسجي
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set purpleBag = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card12) {// اصفر
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set yellowBag = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card13) {// اخضر
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set greenBag = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card15) {// انابيب
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set tube = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        } else if (card == card16) {// تصريف
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE storge set drainBag = '" + quantity + "' WHERE username = '" + username + "'";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("Rows affected: " + result);
        }
    }

    public void retrieveData(user storage) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hqlSelect = "from storge where username='" + storage.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();

        tx.commit();
        session.close();
        storge record = (storge) resultsSelect.get(0);
        System.out.println(String.valueOf(record.getYellowBag()));

        lbcard1.setText(String.valueOf(record.getFaceMasks()));
        tfcard1.setText(String.valueOf(record.getFaceMasks()));
        lbcard3.setText(String.valueOf(record.getSterilizer()));
        tfcard3.setText(String.valueOf(record.getSterilizer()));
        lbcard4.setText(String.valueOf(record.getMedicalMattresse()));
        tfcard4.setText(String.valueOf(record.getMedicalMattresse()));
        lbcard5.setText(String.valueOf(record.getClips()));
        tfcard5.setText(String.valueOf(record.getClips()));
        lbcard6.setText(String.valueOf(record.getGauze()));
        tfcard6.setText(String.valueOf(record.getGauze()));
        lbcard7.setText(String.valueOf(record.getCover()));
        tfcard7.setText(String.valueOf(record.getCover()));
        lbcard8.setText(String.valueOf(record.getSaline()));
        tfcard8.setText(String.valueOf(record.getSaline()));
        lbcard9.setText(String.valueOf(record.getOintment()));
        tfcard9.setText(String.valueOf(record.getOintment()));
        lbcard10.setText(String.valueOf(record.getTapes()));
        tfcard10.setText(String.valueOf(record.getTapes()));
        lbcard11.setText(String.valueOf(record.getPurbleBag()));
        tfcard11.setText(String.valueOf(record.getPurbleBag()));
        String y = "" + record.getYellowBag() + "";
        lbcard12.setText(String.valueOf(record.getYellowBag()));
        tfcard12.setText(y);
        lbcard13.setText(String.valueOf(record.getGreenBag()));
        tfcard13.setText(String.valueOf(record.getGreenBag()));
        lbcard15.setText(String.valueOf(record.getTube()));
        tfcard15.setText(String.valueOf(record.getTube()));
        lbcard16.setText(String.valueOf(record.getDrainBag()));
        tfcard16.setText(String.valueOf(record.getDrainBag()));
        checkQ(card1, tfcard1,us.getUsername());
        checkQ(card3, tfcard3,us.getUsername());
        checkQ(card4, tfcard4,us.getUsername());
        checkQ(card5, tfcard5,us.getUsername());
        checkQ(card6, tfcard6,us.getUsername());
        checkQ(card7, tfcard7,us.getUsername());
        checkQ(card8, tfcard8,us.getUsername());
        checkQ(card9, tfcard9,us.getUsername());
        checkQ(card10, tfcard10,us.getUsername());
        checkQ(card11, tfcard11,us.getUsername());
        checkQ(card12, tfcard12,us.getUsername());
        checkQ(card13, tfcard13,us.getUsername());
        checkQ(card15, tfcard15,us.getUsername());
        checkQ(card16, tfcard16,us.getUsername());
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilePage.fxml"));
        Parent Profileroot = loader.load();
        Scene ProfileScene = new Scene(Profileroot);
        ProfilePageController ProfileCon = loader.getController();
        ProfileCon.showUser(us);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ProfileScene);
        window.show();
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

    public void checkQ(AnchorPane card, TextField q, String username) {
        Label lblfront = (Label) ((VBox) card.getChildren().get(1)).getChildren().get(2); //front quantity label
        VBox back = (VBox) card.getChildren().get(0); // card back
        int qnumb = Integer.valueOf(q.getText()); //quantity value

        NotificationModel n = new NotificationModel();
        n.setUsername(username);

        if (card == card1) { //كمامات
            if (qnumb < 20) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الكمامات");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الكمامات من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");

            } else if (qnumb >= 20 && qnumb < 40) { //أصفر: كمية متوسطة                
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------
        } else if (card == card3) { // المعقم
            if (qnumb == 0) { //أحمر: كمية قليلة
                n.setText("نفذ المعقم");
                n.setIcon("ALERT_CIRCLE");
                n.setIconColor("#ff7070");
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card4) { //المفارش
            if (qnumb < 30) { //أحمر: كمية قليلة
                 if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت المفارش");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت المفارش من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 30 && qnumb < 60) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        }else if (card == card5) { //الكلبسات
            if (qnumb < 13) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الكلبسات");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الكلبسات من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 13 && qnumb < 26) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card6) {// شاش
            if (qnumb < 80) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذ الشاش");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقترب الشاش من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 80 && qnumb < 160) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card7) {// غطا
            if (qnumb < 23) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الاغطية");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الكمامات من الاغطية");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 23 && qnumb < 46) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card8) {// محلول
            if (qnumb < 8) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت المحاليل");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت المحاليل الملحية من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 8 && qnumb < 16) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card9) {// مرهم 
            if (qnumb == 0) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت المراهم");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت المراهم من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb == 1) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card10) {// لاصق
            if (qnumb == 0) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الاشرطة اللاصقة");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الاشرطة اللاصقة من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb == 1) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card11) {// بنفسجي
            if (qnumb < 20) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الاكياس البنفسجية");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الاكياس البنفسجية من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 20 && qnumb < 40) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card12) {// اصفر
            if (qnumb < 20) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الاكياس الصفراء");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الاكياس الصفراء من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 20 && qnumb < 40) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card13) {// اخضر
            if (qnumb < 20) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الاكياس الخضراء");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الاكياس الخضراء من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 20 && qnumb < 40) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card15) {// انابيب
            if (qnumb < 11) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت الانابيب");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت الانابيب من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 11 && qnumb < 22) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
//-----------------------------------------------------------------------------------------------------

        } else if (card == card16) {// تصريف
            if (qnumb < 11) { //أحمر: كمية قليلة
                if (Integer.valueOf(q.getText()) == 0) {
                    n.setText("نفذت اكياس التصريف");
                    n.setIcon("ALERT_CIRCLE");
                    n.setIconColor("#ff7070");
                } else {
                    n.setText("اقتربت اكياس التصريف من النفاذ");
                    n.setIcon("ALERT");
                    n.setIconColor("#ffbd67");
                }
                back.setStyle("-fx-background-color: #e85655;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #e85655");
                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(n);
                System.out.println("inserted notificarion :" + username);
                tx.commit();
                session.close();
            } else if (qnumb >= 11 && qnumb < 22) { //أصفر: كمية متوسطة
                back.setStyle("-fx-background-color: #F6DBA6;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #F6DBA6");
            } else { //أخضر: كمية كبيرة
                back.setStyle("-fx-background-color: #94BE9C;-fx-background-radius:10");
                lblfront.setStyle("-fx-background-color: #94BE9C");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
