/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FirstStepController implements Initializable {

    beforeSession befor = new beforeSession();
    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private JFXCheckBox ch2;

    @FXML
    private JFXCheckBox ch3;

    @FXML
    private JFXTextField pressure1;

    @FXML
    private JFXTextField pressure2;

    @FXML
    private JFXComboBox<Integer> volume;

    @FXML
    private JFXTextField pulse;

    @FXML
    private JFXTextField weight;

    @FXML
    private JFXCheckBox ch1;

    @FXML
    private Label msg;

    user us;

    public void showUser(user u) {
        us = u;
    }

    @FXML
    private JFXComboBox<Integer> bags;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Integer> bagsList = FXCollections.observableArrayList();
        for (int i = 0; i <= 6; i++) {
            bagsList.add(new Integer(i));
        }
        bags.setItems(bagsList);
        bags.setValue(0);

        ObservableList<Integer> volumeList = FXCollections.observableArrayList();

        volumeList.add(5000);
        volumeList.add(2500);

        volume.setItems(volumeList);

    }

    @FXML
    void back(MouseEvent event) throws IOException {
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
    void next(MouseEvent event) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        if (pressure1.getText().isEmpty() || pressure2.getText().isEmpty() || pulse.getText().isEmpty() || weight.getText().isEmpty() || bags.getValue() == 0) {
            msg.setText("من فضلك أكمل تعبئة البيانات");
        } else {

            befor.setUsername(us.getUsername());
            befor.setDate(java.time.LocalDate.now().toString());
            befor.setPressure1(pressure1.getText());
            befor.setPressure2(pressure2.getText());
            befor.setPulse(pulse.getText());
            befor.setWeight(weight.getText());
            befor.setBagsVol(String.valueOf(volume.getValue()));
            befor.setBagsNum(String.valueOf(bags.getValue()));
            if (ch1.isSelected()) {
                befor.setSwollenFeet("Checked");
            } else {
                befor.setSwollenFeet("UnChecked");
            }

            if (ch2.isSelected()) {
                befor.setDyspnea("Checked");
            } else {
                befor.setDyspnea("UnChecked");
            }

            if (ch3.isSelected()) {
                befor.setDry("Checked");
            } else {
                befor.setDry("UnChecked");
            }

            session.save(befor);
            session.getTransaction().commit();
            session.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SecondStep.fxml"));
            Parent Secondroot = loader.load();
            scene = new Scene(Secondroot);
            SecondStepController SecondCon = loader.getController();
            SecondCon.showUser(us);
            SecondCon.select(us);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        }

    }
}
