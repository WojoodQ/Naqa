/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import java.io.IOException;
import java.net.URL;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.event.ChangeListener;

public class MapController implements Initializable{
    
    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private MenuButton map_pin;
    @FXML
    private ComboBox comb;
    @FXML
    private BorderPane root_pane;
    Parent root=null;
    private Stage stage1;
    private Scene scene;
    private Parent root1;
    String[] cities={"مكةالمكرمة","الرياض","جدة"};
    @FXML private AnchorPane sPane;
    Stage stage;
    
    user us;
    public void showUser(user u) {
        us = u;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String>cit=FXCollections.observableArrayList(cities);    
        comb.setItems(cit);
        comb.setValue("مكة المكرمة");
         
            try {
                root=FXMLLoader.load(getClass().getResource("MapMakkah.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            }
            root_pane.setCenter(root);
        
    }
    @FXML
    void switch1(ActionEvent event) {
        
        int index = comb.getSelectionModel().getSelectedIndex();
        
        if(index==0){
            try {
                root=FXMLLoader.load(getClass().getResource("MapMakkah.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            }
            root_pane.setCenter(root);
        }
        else if(index==1){
            try {
                root=FXMLLoader.load(getClass().getResource("MapRiyadh.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            }
            root_pane.setCenter(root);
        }
        else if(index==2){
            try {
                root=FXMLLoader.load(getClass().getResource("MapJeddah.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            }
            root_pane.setCenter(root);
        }
        
    }
    
}
