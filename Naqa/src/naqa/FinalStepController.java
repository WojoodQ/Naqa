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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FinalStepController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    user us;
    monthlyconsumption monthly = new monthlyconsumption();

    public void showUser(user u) {
        us = u;
    }

    @FXML
    void next(MouseEvent event) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hqlSelect = "from storge where username='" + us.getUsername() + "'";
        Query querySelect = session.createQuery(hqlSelect);
        List resultsSelect = querySelect.list();
        tx.commit();

        Transaction tx2 = session.beginTransaction();
        String hqlSelect2 = "from DailySuppliesModel where username='" + us.getUsername() + "'";
        Query querySelect2 = session.createQuery(hqlSelect2);
        List resultsSelect2 = querySelect2.list();
        tx2.commit();

        storge record = (storge) resultsSelect.get(0);
        DailySuppliesModel record2 = (DailySuppliesModel) resultsSelect2.get(0);
        if ((record.getDrainBag() - record2.getDrainBag()) >= 0) {
            Transaction tx3 = session.beginTransaction();
            String hql = "UPDATE storge set drainBag = '" + (record.getDrainBag() - record2.getDrainBag()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            tx3.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من اكياس التصريف");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------
        if ((record.getTube() - record2.getTupe()) >= 0) {
            Transaction tx4 = session.beginTransaction();
            String hq2 = "UPDATE storge set tube = '" + (record.getTube() - record2.getTupe()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query2 = session.createQuery(hq2);
            query2.executeUpdate();
            tx4.commit();
        }else{
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الانابيب");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//----------------------------------------------------------------------------------------------------------------------------------------        
        if ((record.getGauze() - record2.getGauze()) >= 0) {

            Transaction tx5 = session.beginTransaction();
            String hq3 = "UPDATE storge set gauze = '" + (record.getGauze() - record2.getGauze()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query3 = session.createQuery(hq3);
            query3.executeUpdate();
            tx5.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الشاش");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------
        if ((record.getMedicalMattresse() - record2.getMedicalMattresse()) >= 0) {

            Transaction tx6 = session.beginTransaction();
            String hq4 = "UPDATE storge set medicalMattresse = '" + (record.getMedicalMattresse() - record2.getMedicalMattresse()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query4 = session.createQuery(hq4);
            query4.executeUpdate();
            tx6.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من المفارش الطبية");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
//            if ((record.getSterilizer() - 1) >= 0) {
//
//                Transaction tx7 = session.beginTransaction();
//                String hq5 = "UPDATE storge set sterilizer = '" + (record.getSterilizer() - 1) + "' WHERE username = '" + us.getUsername() + "'";
//                Query query5 = session.createQuery(hq5);
//                query5.executeUpdate();
//                tx7.commit();
//            } else {
//                
//            }
//--------------------------------------------------------------------------------------------------------------------------------------
//            if ((record.getTapes() - 1) >= 0) {
//
//                Transaction tx8 = session.beginTransaction();
//                String hq6 = "UPDATE storge set tapes = '" + (record.getTapes() - 1) + "' WHERE username = '" + us.getUsername() + "'";
//                Query query6 = session.createQuery(hq6);
//                query6.executeUpdate();
//                tx8.commit();
//            } else {
//            }
//--------------------------------------------------------------------------------------------------------------------------------------
        if ((record.getFaceMasks() - record2.getFaceMask()) >= 0) {

            Transaction tx9 = session.beginTransaction();
            String hq7 = "UPDATE storge set faceMask = '" + (record.getFaceMasks() - record2.getFaceMask()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query7 = session.createQuery(hq7);
            query7.executeUpdate();
            tx9.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الكمامات");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
        if ((record.getClips() - record2.getClips()) >= 0) {

            Transaction tx10 = session.beginTransaction();
            String hq8 = "UPDATE storge set clips = '" + (record.getClips() - record2.getClips()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query8 = session.createQuery(hq8);
            query8.executeUpdate();
            tx10.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الكلبسات");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
        if ((record.getCover() - record2.getCover()) >= 0) {

            Transaction tx11 = session.beginTransaction();
            String hq9 = "UPDATE storge set cover = '" + (record.getCover() - record2.getCover()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query9 = session.createQuery(hq9);
            query9.executeUpdate();
            tx11.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من اغطية القسطرة");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
        if ((record.getSaline() - record2.getSaline()) >= 0) {

            Transaction tx12 = session.beginTransaction();
            String hq10 = "UPDATE storge set saline = '" + (record.getSaline() - record2.getSaline()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query10 = session.createQuery(hq10);
            query10.executeUpdate();
            tx12.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من المحاليل الملحية");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
//            if ((record.getOintment() - 1) >= 0) {
//
//                Transaction tx13 = session.beginTransaction();
//                String hq11 = "UPDATE storge set ointment = '" + (record.getOintment() - 1) + "' WHERE username = '" + us.getUsername() + "'";
//                Query query11 = session.createQuery(hq11);
//                query11.executeUpdate();
//                tx13.commit();
//            } else {
//            }
//--------------------------------------------------------------------------------------------------------------------------------------
        if ((record.getPurbleBag() - 1) >= 0) {

            Transaction tx14 = session.beginTransaction();
            String hq12 = "UPDATE storge set purpleBag = '" + (record.getPurbleBag() - 1) + "' WHERE username = '" + us.getUsername() + "'";
            Query query12 = session.createQuery(hq12);
            query12.executeUpdate();
            tx14.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الاكياس البنفسجية");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
        if ((record.getYellowBag() - record2.getYellowBag()) > 0) {

            Transaction tx15 = session.beginTransaction();
            String hq13 = "UPDATE storge set yellowBag = '" + (record.getYellowBag() - record2.getYellowBag()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query13 = session.createQuery(hq13);
            query13.executeUpdate();
            tx15.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الاكياس الصفراء");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            
        if ((record.getGreenBag() - record2.getGreenBag()) >= 0) {

            Transaction tx16 = session.beginTransaction();
            String hq14 = "UPDATE storge set greenBag = '" + (record.getGreenBag() - record2.getGreenBag()) + "' WHERE username = '" + us.getUsername() + "'";
            Query query14 = session.createQuery(hq14);
            query14.executeUpdate();
            tx16.commit();
        } else {
            NotificationModel n = new NotificationModel();
            n.setUsername(us.getUsername());
            n.setText("ليس لديك كمية كافية من الاكياس الخضراء");
            n.setIcon("BULLHORN");
            n.setIconColor("#b76ec3");
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx0 = session2.beginTransaction();
            session2.save(n);
            System.out.println("inserted notificarion :" + us.getUsername());
            tx0.commit();
            session2.close();
        }
//--------------------------------------------------------------------------------------------------------------------------------------            

        Transaction tx17 = session.beginTransaction();
        monthly.setUsername(us.getUsername());
        monthly.setDate(java.time.LocalDate.now().toString());
        monthly.setGreenBag(record2.getGreenBag());
        monthly.setYellowBag(record2.getYellowBag());
        monthly.setPurbleBag(1);
        monthly.setPurbleBag(record2.getDrainBag());
        monthly.setTube(record2.getTupe());
        monthly.setGauze(record2.getGauze());
        monthly.setMedicalMattresse(record2.getMedicalMattresse());
//        monthly.setSterilizer(1);
        monthly.setSaline(record2.getSaline());
        monthly.setFaceMasks(record2.getFaceMask());
//        monthly.setTapes(1);
        monthly.setClips(record2.getClips());
        monthly.setCover(record2.getCover());
//        monthly.setOintment(1);

        session.save(monthly);
        tx17.commit();

        session.close();
        System.out.println("monthlyConsumption Done");
        System.out.println("storge Done");
        ///////////////////////////////////////////////////////////////////////////////
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

    @FXML
    void back(MouseEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
