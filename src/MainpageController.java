
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainpageController implements Initializable {

    @FXML
    private Button btnsignin;
    @FXML
    private Button btncreate;

    /**
     * Initializes the controller class.
     */
    public String username;
    public void setusername(String a){
        this.username=a;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
         if(event.getSource()==btnsignin){
            
             Parent tv=FXMLLoader.load(getClass().getResource("userwindow.fxml"));
            Scene tvs=new Scene(tv);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tvs);
            window.show();
            
        }
         else if(event.getSource()==btncreate){
            
             Parent tv=FXMLLoader.load(getClass().getResource("adminwindow.fxml"));
            Scene tvs=new Scene(tv);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tvs);
            window.show();
            
        }
    }
    
}
