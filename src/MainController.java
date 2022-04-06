
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.DefaultTableModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tfsurname;
    @FXML
    private TextField tfrole;
    @FXML
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
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    public String username;
    @FXML
    private Button btnback;
    @FXML
    private Button buyp;
    
    public void setusername(String a){
        this.username=a;
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btninsert){
            insertRecord();
            showemployes();
        }
        else if(event.getSource()==btnupdate){
            updateRecord();
            showemployes();
        }
        else if(event.getSource()==btndelete){
            deleteRecord();
            showemployes();
        }
        else if(event.getSource()==buyp){
            deleteRecord1();
            showemployes();
            
        }
        else if(event.getSource()==btnback){
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
        loadtable();
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
        String query="select * from properties where username="+"'"+this.username+"'";
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
        ObservableList<Employes> employeslist=getemployeslist();
        colfirstname.setCellValueFactory(new PropertyValueFactory<Employes,String>("Address"));
        colsurname.setCellValueFactory(new PropertyValueFactory<Employes,String>("Area"));
        colrole.setCellValueFactory(new PropertyValueFactory<Employes,String>("Purpose"));
        colwh.setCellValueFactory(new PropertyValueFactory<Employes,Integer>("Price"));
        tvemployes.setItems(employeslist);
        
    }
    public void insertRecord(){
        String query="INSERT INTO properties VALUES("+"'" + tffirstname.getText() + "','"+tfsurname.getText()+ "','"+tfrole.getText()+"',"+tfwh.getText()+",'"+this.username+"')";
        executeQuery(query);
        loadtable();
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
    public void loadtable(){
           tvemployes.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               Employes e=tvemployes.getItems().get(tvemployes.getSelectionModel().getSelectedIndex());
               tffirstname.setText(e.getAddress());
               tfsurname.setText(e.Area);
               tfrole.setText(e.Purpose);
               int hah=e.Price;
               String k=String.valueOf(hah);
               tfwh.setText(k);
           }
       });
        
    }
     public void updateRecord(){
       try {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();

        String query = "UPDATE properties SET Address=?, Area=?, Purpose=?, Price=? WHERE Address=? and Area=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,tffirstname.getText());
        preparedStatement.setString(2,tfsurname.getText());
        preparedStatement.setString(3,tfrole.getText());
        preparedStatement.setInt(4, Integer.parseInt(tfwh.getText()));
         preparedStatement.setString(5,tffirstname.getText());
         preparedStatement.setString(6,tfsurname.getText());
        preparedStatement.executeUpdate();
    }catch (Exception e){
        System.out.println("Can't update");
        e.printStackTrace();
    }
    }
     
     public void deleteRecord(){
       try {
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();

        String query = "delete from properties WHERE Address=? and Area=?";
          preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1,tffirstname.getText());
         preparedStatement.setString(2,tfsurname.getText());
        preparedStatement.executeUpdate();
    }catch (Exception e){
        System.out.println("Can't delete");
        e.printStackTrace();
    }
    }
     
     public void deleteRecord1(){
       try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Property Bought By  "+username);
        alert.setHeaderText("Congratulations");
        alert.showAndWait();
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();

        String query = "delete from properties WHERE Address=? and Area=?";
          preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1,tffirstname.getText());
         preparedStatement.setString(2,tfsurname.getText());
        preparedStatement.executeUpdate();
    }catch (Exception e){
        System.out.println("Can't delete");
        e.printStackTrace();
    }
    } 
}
