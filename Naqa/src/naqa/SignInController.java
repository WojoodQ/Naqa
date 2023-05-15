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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author wojoo
 */
public class SignInController implements Initializable {

    user u;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button signin_but;

    @FXML
    private Button forget_but;

    @FXML
    private Label mess;

    Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void signInBut(ActionEvent event) throws IOException {
        mess.setText(" ");
        user u = new user();
        if (username.getText().isEmpty() == true || password.getText().isEmpty() == true) {
            mess.setText("تأكد من ادخال جميع الحقول");
        } else {
            mess.setText(" ");
            validation(event);

        }

    }

    public void validation(ActionEvent event) throws IOException {
        mess.setText(" ");
        user u = new user();

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hq = "from user where username='" + username.getText() + "'";
        Query queryDel = session.createQuery(hq);

        List<user> users = queryDel.list();
        session.close();

        System.out.println("user list size: " + users.size());

        for (user us : users) {
            mess.setText(" ");
            System.out.println("UserName: " + us.getUsername());
            System.out.println("Password: " + us.getPassword());
            System.out.println("name: " + us.getF_name());
            u = us;
            
        }
        if (users.size() == 0) {
            mess.setText("فضلا تأكد من ادخال اسم المستخدم  بشكل صحيح");
        } else {
            mess.setText(" ");
            if (!(users.get(0).getPassword().equals(password.getText()))) {
                mess.setText("كلمة السر غير صحيحة");
            } else {
                mess.setText(" ");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Home.fxml"));
                Parent Homeroot = loader.load();
                scene = new Scene(Homeroot);
                HomeController HomeCon = loader.getController();
                HomeCon.showUser(u);
                HomeCon.retrive(u);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
