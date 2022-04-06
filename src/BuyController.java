import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.DefaultTableModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BuyController implements Initializable {
    
    private Label label;
    private TextField tffirstname;
    private TextField tfsurname;
    private TextField tfrole;
    private TextField tfwh;
    @FXML
    private TableView<Employes> tvemployes;
    @FXML
    private TableColumn<Employes, String> colfirstname;
    @FXML
    private TableColumn<Employes,String> colsurname;
    @FXML
    private TableColumn<Employes, String> colrole;
    @FXML
    private TableColumn<Employes, Integer> colwh;
    @FXML
    private Button btnback;
    String username;
    String a="Selling";
    @FXML
    private TextField searchbar;
    
    public void setusername(String a){
        this.username=a;
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
       
        if(event.getSource()==btnback){
              FXMLLoader Loader=new FXMLLoader();
               Loader.setLocation(getClass().getResource("customermenu.fxml"));
               try{
                   Loader.load();
               }
               catch(IOException ex){
                   Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                   
               }
               CustomermenuController Display=Loader.getController();
               Display.setusername(username);
               Parent p=Loader.getRoot();
               Stage stage=new Stage();
               stage.setScene(new Scene(p));
               stage.showAndWait();
            
        
        
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showemployes();
       
        
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
    public ObservableList<Employes> getemployeslist(){
        ObservableList<Employes> employeslist=FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="select * from properties where Purpose="+"'"+this.a+"'";
        Statement st;
        ResultSet rs;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            Employes employes;
            while(rs.next()){
                employes=new Employes(rs.getString("Address"),rs.getString("Area"),rs.getString("Purpose"),rs.getInt("Price"));
                employeslist.add(employes);
                        
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return employeslist;
    }
    public void showemployes(){
        ObservableList<Employes> employeslist1;
        ObservableList<Employes> employeslist=getemployeslist();
        colfirstname.setCellValueFactory(new PropertyValueFactory<Employes,String>("Address"));
        colsurname.setCellValueFactory(new PropertyValueFactory<Employes,String>("Area"));
        colrole.setCellValueFactory(new PropertyValueFactory<Employes,String>("Purpose"));
        colwh.setCellValueFactory(new PropertyValueFactory<Employes,Integer>("Price"));
        FilteredList<Employes> flPerson = new FilteredList(employeslist, p -> true);//Pass the data to a filtered list
        tvemployes.setItems(flPerson);//Set the table's items using the filtered list
        searchbar.textProperty().addListener((obs, oldValue, newValue) -> {
        flPerson.setPredicate(p -> p.getAddress().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
               
            
        });
      
    }
 
    public void executeQuery(String query){
        Connection conn=getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
    }
   
     
               
}
