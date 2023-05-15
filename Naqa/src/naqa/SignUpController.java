/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 *
 * @author wojoo
 */
public class SignUpController implements Initializable {

    storge storge = new storge();
    DailySuppliesModel dsuser = new DailySuppliesModel();

    WelcomeController welcomeCon;
    @FXML
    private RadioButton Diab;

    @FXML
    private ComboBox<String> cob;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private RadioButton healthy;

    @FXML
    private Label mass;

    @FXML
    private PasswordField password;

    @FXML
    private ToggleGroup tgbut;

    @FXML
    private TextField username;

    @FXML
    private PasswordField passwordchs;

    @FXML
    private Button signup_but;
    @FXML
    private JFXComboBox<String> cob2;

    String radioCho;

    private ObservableList listC = FXCollections.observableArrayList("مكة المكرمة", "الرياض", "الجوف", "الشرقية", "عسير");

    public Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cob2.setItems(listC);
        Diab.isSelected();
        healthy.isSelected();
        radioCho = "";

    }

    @FXML
    private void radioSet(ActionEvent event) {

        if (event.getSource() == Diab) {
            mass.setText(" ");
            radioCho = Diab.getText();
        } else {
            mass.setText(" ");
            radioCho = healthy.getText();

        }
    }

    public void initData(user u) {
        mass.setText(" ");
        dsuser.setUsername(u.getUsername());
        dsuser.setDrainBag(1);
        dsuser.setTupe(1);
        dsuser.setGauze(0);
        dsuser.setMedicalMattresse(0);
        dsuser.setSaline(0);
        dsuser.setFaceMask(0);
        dsuser.setClips(1);
        dsuser.setCover(1);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(dsuser);
        tx.commit();
        session.close();
        System.out.println("inserted daily user supply: " + dsuser.getUsername());
    }

    @FXML
    private void signupBut(ActionEvent event) throws IOException {
       
        
        user u = new user();
        mass.setText(" ");
        
        if (username.getText().isEmpty() == true || password.getText().isEmpty() == true || passwordchs.getText().isEmpty() == true || name.getText().isEmpty() == true || email.getText().isEmpty() == true || cob2.getValue() == null || radioCho.isEmpty() == true) {
            mass.setText("تأكد من ادخال جميع الحقول");
        } else if (!(password.getText().equals(passwordchs.getText()))) {
            mass.setText("تأكد من تطابق كلمة السر المدخله");
        } else if (password.getText().length() < 8 && passwordchs.getText().length() < 8) {
            mass.setText("يجب عليك ادخال رمز مكون من ثمانية خانات او اكثر ");

        } else if ( !(email.getText().contains("@")) || !(email.getText().contains("."))) {
            mass.setText("فضلا ادخل البريد الالكتروني بالشكل الصحيح");
        }else {
            mass.setText("  ");
            try {
                u.setF_name(name.getText());
                u.setUsername(username.getText());

                u.setPassword(password.getText());
                u.setEmail(email.getText());
                u.setCob2(cob2.getSelectionModel().getSelectedItem());
                u.setHealthy(radioCho);

                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(u);
                tx.commit();
                session.close();

                mass.setText(" ");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Welcome.fxml"));
                Parent Welcomeroot = loader.load();

                Scene WelcomeScene = new Scene(Welcomeroot);

                WelcomeController welcomeCon = loader.getController();
                welcomeCon.showUser(u);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(WelcomeScene);
                mass.setText(" ");
                window.show();
                System.out.println("inserted patient :" + u.getF_name());

                Session session2 = HibernateUtil.getSessionFactory().openSession();
                session2 = HibernateUtil.getSessionFactory().openSession();
                session2.beginTransaction();

                storge.setUsername(u.getUsername());
                storge.setGreenBag(0);
                storge.setYellowBag(0);
                storge.setPurbleBag(0);
                storge.setDrainBag(0);
                storge.setTube(0);
                storge.setTapes(0);
                storge.setGauze(0);
                storge.setMedicalMattresse(0);
                storge.setSterilizer(0);
                storge.setSaline(0);
                storge.setFaceMasks(0);
                storge.setClips(0);
                storge.setCover(0);
                storge.setOintment(0);

                session2.save(storge);
                session2.getTransaction().commit();
                session2.close();

                mass.setText(" ");
                initData(u);

                date appointment = new date();
                appointment.setUsername(u.getUsername());
                Session session3 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx3 = session3.beginTransaction();
                appointment.setMonthlyDate("");
                appointment.setYearDate("");
                session3.save(appointment);
                tx3.commit();
                session3.close();
                mass.setText(" ");
                System.out.println("inserted user appointments: " + appointment.getUsername());

                
                System.out.println("inserted patient storge :" + u.getF_name());
                mass.setText(" ");

            } catch (Exception e) {
                mass.setText("اسم المستخدم مستعمل , ادخل اسم مستخدم اخر");

            }
        }

    }

}
