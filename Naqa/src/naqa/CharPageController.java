/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class CharPageController implements Initializable {

    @FXML
    private AnchorPane sPane;
    @FXML
    private PieChart pieChart;

    private Stage stage;
    private Scene scene;
    private Parent root;

    user us;

    public void showUser(user us) {

        Session session;
        ObservableList<PieChart.Data> pieChartDate = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<monthlyconsumption> list = null;
        String queryp = "from monthlyconsumption";
        Query query = session.createQuery(queryp);
        list = query.list();
        session.getTransaction().commit();
        session.close();
        String datestr = "";
        double greenBag = 0, yellowBag = 0;
        int purbleBag = 0, tube = 0, gauze = 0, medicalMattresse = 0, sterilizer = 0, tapes = 0;
        int faceMasks = 0, clips = 0, cover = 0, Saline = 0, ointment = 0;

        int days = 0;
        for (monthlyconsumption u : list) {
            if (u.getUsername().equals(us.getUsername())) {
                datestr = u.getDate();
                String[] Date = datestr.split("-");
                String d = java.time.LocalDate.now().toString();
                String[] Date2 = d.split("-");
                if (Date[1].equals((Date2[1]))) {
                    days++;
                    greenBag = greenBag + u.getGreenBag();
                    yellowBag = yellowBag + u.getYellowBag();
                    purbleBag = purbleBag + u.getPurbleBag();
                    tube = tube + u.getTube();
                    gauze = gauze + u.getGauze();
                    medicalMattresse = medicalMattresse + u.getMedicalMattresse();
                    sterilizer = sterilizer + u.getSterilizer();
                    tapes = tapes + u.getTapes();
                    faceMasks = faceMasks + u.getFaceMasks();
                    clips = clips + u.getClips();
                    cover = cover + u.getCover();
                    Saline = Saline + u.getSaline();
                    ointment = ointment + u.getOintment();
                }
            }
        }
        System.out.println("number of days: " + days);
        System.out.println(greenBag / days);
        pieChartDate = FXCollections.observableArrayList(
                new PieChart.Data("أكياس التنقية الخضراء" + " (" + (greenBag / days) + ") ", (greenBag / days)),
                new PieChart.Data("أكياس التنقية الصفراء" + " (" + yellowBag / days + ") ", yellowBag / days),
                new PieChart.Data("أكياس التنقية االخضراءلبنفسجية" + " (" + purbleBag / days + ") ", purbleBag / days),
                new PieChart.Data("الأنابيب" + " (" + tube / days + ") ", tube / days),
                new PieChart.Data("شاش معقم" + " (" + gauze / days + ") ", gauze / days),
                new PieChart.Data("مفارش طبية" + " (" + medicalMattresse / days + ") ", medicalMattresse / days),
                new PieChart.Data("معقم" + " (" + sterilizer / days + ") ", sterilizer / days),
                new PieChart.Data("أشرطة لاصقة طبية" + " (" + tapes / days + ") ", tapes / days),
                new PieChart.Data("كمامات" + " (" + faceMasks / days + ") ", faceMasks / days),
                new PieChart.Data("كلبسات معقمة" + " (" + clips / days + ") ", clips / days),
                new PieChart.Data("أغطية القسطرة" + " (" + cover / days + ") ", cover / days),
                new PieChart.Data("محلول ملحي" + " (" + Saline/ days + ") ", Saline / days),
                new PieChart.Data("مرهم الأنابيب" + " (" + ointment/ days + ") ", ointment / days));

        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setData(pieChartDate);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
