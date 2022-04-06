/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AdminwindowController implements Initializable {

    @FXML
    private Button registeradmin;
    @FXML
    private Button loginadmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionadmin(ActionEvent event) throws IOException {
         if(event.getSource()==registeradmin){
            
             Parent tv=FXMLLoader.load(getClass().getResource("signupadmin.fxml"));
            Scene tvs=new Scene(tv);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tvs);
            window.show();
            
        }
         else if(event.getSource()==loginadmin){
            
             Parent tv=FXMLLoader.load(getClass().getResource("signinadmin.fxml"));
            Scene tvs=new Scene(tv);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tvs);
            window.show();
            
        }
    }   
}
