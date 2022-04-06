
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SigninController implements Initializable {

    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btncreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btncreate){
            
           signin();
            
        }
    }
     public Connection getConnection(){
        Connection conn;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost/property1","root","");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error:"+ex.getMessage());
            return null;
        }
     }  
     public void signin(){
         
        PreparedStatement pst;
        String username=txtusername.getText();
        String password=txtpassword.getText();
        Connection con=getConnection();
        ResultSet rs;
        try {
            // TODO add your handling code here:
            
            pst=con.prepareStatement("select * from user where username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs=pst.executeQuery();
            if(rs.next()){
               String name=txtusername.getText();       
               FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("customermenu.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               CustomermenuController Display=Loader.getController();
               Display.setusername(name);
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect username or password");
            alert.setHeaderText("Kindly check");
            alert.showAndWait();
            }
           
        } 
         catch(Exception ex){
            System.out.println("Error:"+ex.getMessage());
            
        }
    }
}
