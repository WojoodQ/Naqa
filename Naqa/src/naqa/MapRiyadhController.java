/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class MapRiyadhController implements Initializable {

    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private FontAwesomeIconView map_pin;

    String [] shops={"مستلزمات طبية مؤسسة قصر المستلزمات للتجارة للمستلزمات الطبية","مستلزمات طبية اوكسجين1","المحسن للمستلزمات الطبية","رسام الريان للمستلزمات الطبية","مستلزمات طبية هشام السريع للوازم الطبية","انوار غرناطة ادوات طبية مستلزمات طبية","مؤسسة شموع اليمامة","رحمة للمستلزمات الطبية","الريان للمستلزمات الطبية","شركة أزها التجارية المحدودة للمستلزمات الطبية","مؤسسة درة القويعية الطبية صيانة وقطع غيار معدات طبية","بروزالقمة للمستلزمات الطبية"};
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        map_pin.setVisible(false);
        ObservableList<String>listItem=FXCollections.observableArrayList(shops);
        map_listview.setItems(listItem);
        
      map_listview.getSelectionModel().selectedItemProperty().addListener(e-> {
           map_listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = map_listview.getSelectionModel().getSelectedIndex();
            switch(index){
                case 0: 
                    map_pin.setLayoutX(527.0);
                    map_pin.setLayoutY(505.0);
                    map_pin.setVisible(true);
                    break;
                case 1:
                    map_pin.setLayoutX(500.0);
                    map_pin.setLayoutY(475.0);
                    map_pin.setVisible(true);
                    break;
                case 2:
                    map_pin.setLayoutX(510.0);
                    map_pin.setLayoutY(240.0);
                    map_pin.setVisible(true);
                    break;
                case 3:
                    map_pin.setLayoutX(234.0);
                    map_pin.setLayoutY(340.0);
                    map_pin.setVisible(true);
                    break;
                case 4:
                   map_pin.setLayoutX(420.0);
                    map_pin.setLayoutY(230.0);
                    map_pin.setVisible(true);
                    break;
                case 5:
                    map_pin.setLayoutX(393.0);
                    map_pin.setLayoutY(50.0);
                    map_pin.setVisible(true);
                    break;
                case 6:
                    map_pin.setLayoutX(515.0);
                    map_pin.setLayoutY(270.0);
                    map_pin.setVisible(true);
                    break;
                case 7:
                    map_pin.setLayoutX(510.0);
                    map_pin.setLayoutY(220.0);
                    map_pin.setVisible(true);
                    break;
                case 8:
                    map_pin.setLayoutX(718.0);
                    map_pin.setLayoutY(210.0);
                    map_pin.setVisible(true);
                    break;
                case 9:
                    map_pin.setLayoutX(750.0);
                    map_pin.setLayoutY(400.0);
                    map_pin.setVisible(true);
                    break;
                case 10:
                    map_pin.setLayoutX(530.0);
                    map_pin.setLayoutY(310.0);
                    map_pin.setVisible(true);
                    break;
                case 11:
                    map_pin.setLayoutX(520.0);
                    map_pin.setLayoutY(265.0);
                    map_pin.setVisible(true);
                    break;
            }
            
           
            
      });
    }
}
