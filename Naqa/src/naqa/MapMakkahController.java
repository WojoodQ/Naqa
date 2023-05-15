package naqa;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class MapMakkahController implements Initializable {

    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private FontAwesomeIconView map_pin;

    String [] shops={"العيينة الطبية","مشكاة البلد الطبية","شركة المخلص للمستلزمات الطبية","بيت العلاج للمستلزمات الطبية جبل النور","مؤسسة نطاق المستشفيات التجارية للمستلزمات والاجهزة الطبية","الشافعي للمعدات الطبية والوسائل التعليمية","اللباس الصحي","مستلزمات الوديع الطبية","مؤسسة تجهزات المستقبل","مؤسسة خطوات التطوير الطبية","المالكي للمستلزمات الطبية","العنوان الطبي","طبية الفؤاد للمعدات الطبية"};
    
    
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
                    map_pin.setLayoutX(402.0);
                    map_pin.setLayoutY(282.0);
                    map_pin.setVisible(true);
                    break;
                case 1:
                    map_pin.setLayoutX(225.0);
                    map_pin.setLayoutY(320.0);
                    map_pin.setVisible(true);
                    break;
                case 2:
                    map_pin.setLayoutX(406.0);
                    map_pin.setLayoutY(282.0);
                    map_pin.setVisible(true);
                    break;
                case 3:
                    map_pin.setLayoutX(515.0);
                    map_pin.setLayoutY(255.0);
                    map_pin.setVisible(true);
                    break;
                case 4:
                    map_pin.setLayoutX(375.0);
                    map_pin.setLayoutY(120.0);
                    map_pin.setVisible(true);
                    break;
                case 5:
                    map_pin.setLayoutX(402.0);
                    map_pin.setLayoutY(244.0);
                    map_pin.setVisible(true);
                    break;
                case 6:
                    map_pin.setLayoutX(380.0);
                    map_pin.setLayoutY(360.0);
                    map_pin.setVisible(true);
                    break;
                case 7:
                    map_pin.setLayoutX(403.0);
                    map_pin.setLayoutY(285.0);
                    map_pin.setVisible(true);
                    break;
                case 8:
                    map_pin.setLayoutX(522.0);
                    map_pin.setLayoutY(375.0);
                    map_pin.setVisible(true);
                    break;
                case 9:
                    map_pin.setLayoutX(402.0);
                    map_pin.setLayoutY(282.0);
                    map_pin.setVisible(true);
                    break;
                case 10:
                    map_pin.setLayoutX(735.0);
                    map_pin.setLayoutY(220.0);
                    map_pin.setVisible(true);
                    break;
                case 11:
                    map_pin.setLayoutX(385.0);
                    map_pin.setLayoutY(370.0);
                    map_pin.setVisible(true);
                    break;
                case 12:
                    map_pin.setLayoutX(390.0);
                    map_pin.setLayoutY(410.0);
                    map_pin.setVisible(true);
                    break;
            }
            
           
            
      });
    }    
    
}
