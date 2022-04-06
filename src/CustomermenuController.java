

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class CustomermenuController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button buy;
    @FXML
    private Button rent;
    public String username;
    @FXML
    private Button bacl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public void setusername(String a){
        this.username=a;
    }

    @FXML
    private void onaction(ActionEvent event) throws IOException {
        if(event.getSource()==add){
            
              String name=username;       
               FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("Main.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               MainController Display=Loader.getController();
               Display.setusername(name);
               Display.showemployes();
               Display.loadtable();
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            
        }
         else if(event.getSource()==buy){
               String name=username;       
               FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("buy.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               BuyController Display=Loader.getController();
               Display.showemployes();
      
               Display.setusername(name);
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            
        }
        else if(event.getSource()==rent){
            
             String name=username;       
               FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("rent.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               RentController Display=Loader.getController();
               Display.showemployes();
             
               Display.setusername(name);
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            
        }
        else if(event.getSource()==bacl){
              FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("mainpage.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               MainpageController Display=Loader.getController();
               Display.setusername(username);
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            
        
        
            
        }
    }
    
}
