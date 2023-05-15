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
public class MapJeddahController implements Initializable {

    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private FontAwesomeIconView map_pin;
    
    String [] shops={"العيينة الطبية","شركة مركز الميرة المعدات والأجهزة الطبية","شركة المخلص للمستلزمات الطبية بجدة","المنطقة الطبية للأدوات والمستلزمات الطبية","الرعاية البيضاء لبيع جميع المستلزمات الطببة","مؤسسة جود المتقدمة المحدودة للمستلزمات الطبية","السندسية الطبية الرائدة للمعدات","السريع الطبية","شركة افق الرعاية","مؤسسة الثقة للتجهيزات الطبية","الشركة السعودية للخدمات الصحية","الحياة للمستلزمات الطبية","درة الطيب للأجهزة الطبية"};
    
    
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
                    map_pin.setLayoutX(630.0);
                    map_pin.setLayoutY(48.0);
                    map_pin.setVisible(true);
                    break;
                case 1:
                    map_pin.setLayoutX(531.0);
                    map_pin.setLayoutY(347.0);
                    map_pin.setVisible(true);
                    break;
                case 2:
                    map_pin.setLayoutX(600.0);
                    map_pin.setLayoutY(462.0);
                    map_pin.setVisible(true);
                    break;
                case 3:
                    map_pin.setLayoutX(607.0);
                    map_pin.setLayoutY(515.0);
                    map_pin.setVisible(true);
                    break;
                case 4:
                   map_pin.setLayoutX(637.0);
                    map_pin.setLayoutY(60.0);
                    map_pin.setVisible(true);
                    break;
                case 5:
                    map_pin.setLayoutX(535.0);
                    map_pin.setLayoutY(188.0);
                    map_pin.setVisible(true);
                    break;
                case 6:
                    map_pin.setLayoutX(607.0);
                    map_pin.setLayoutY(480.0);
                    map_pin.setVisible(true);
                    break;
                case 7:
                    map_pin.setLayoutX(607.0);
                    map_pin.setLayoutY(475.0);
                    map_pin.setVisible(true);
                    break;
                case 8:
                    map_pin.setLayoutX(488.0);
                    map_pin.setLayoutY(227.0);
                    map_pin.setVisible(true);
                    break;
                case 9:
                    map_pin.setLayoutX(553.0);
                    map_pin.setLayoutY(198.0);
                    map_pin.setVisible(true);
                    break;
                case 10:
                    map_pin.setLayoutX(203.0);
                    map_pin.setLayoutY(92.0);
                    map_pin.setVisible(true);
                    break;
                case 11:
                    map_pin.setLayoutX(635.0);
                    map_pin.setLayoutY(470.0);
                    map_pin.setVisible(true);
                    break;
                case 12:
                    map_pin.setLayoutX(607.0);
                    map_pin.setLayoutY(470.0);
                    map_pin.setVisible(true);
                    break;
            }
            
           
            
      });
    }
}
