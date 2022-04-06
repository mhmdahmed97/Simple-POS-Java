
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupadminController implements Initializable {

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnlogin;
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource()==btncreate){
            insertRecord();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Creation Successful");
            alert.setHeaderText("Proceed to Signin");
            alert.showAndWait();
            
        }
        if(event.getSource()==btnlogin){
            
            Parent tv=FXMLLoader.load(getClass().getResource("signinadmin.fxml"));
            Scene tvs=new Scene(tv);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tvs);
            window.show();
            
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
       private void insertRecord(){
         try {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();

        String query = "insert into admin values(?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,txtname.getText());
        preparedStatement.setString(2,txtusername.getText());
        preparedStatement.setString(3,txtpassword.getText());
        
        preparedStatement.executeUpdate();
    }
         catch (Exception e){
        System.out.println("Can't insert");
        e.printStackTrace();
    }
    }
  
}
