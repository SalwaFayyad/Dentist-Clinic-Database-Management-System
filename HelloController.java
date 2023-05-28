
package com.example.dataproject;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;
import javafx.event.*;
import java.sql.*;
import javafx.scene.*;
import java.util.*;
import javafx.collections.*;
import java.net.*;



public class HelloController implements Initializable {

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;
///////////////////////////////////////////////////////////////////////////////



    @FXML
    private TableView<Patients> PatientTable;

    public Button btn = new Button();

    @FXML
    private TableColumn<Patients, Integer> PID;

    @FXML
    private TableColumn<Patients, String > Birthdate;

    @FXML
    private TableColumn<Patients, String > Pemail;

    @FXML

    private TableColumn<Patients, String > Pgender;

    @FXML
    private TableColumn<Patients, String > Phone;


    @FXML
    private TableColumn<Patients, String > Pname;

    @FXML
    private Button SearchID;
    @FXML
    private Button refresh;
    @FXML
    private TextField idtext;

    ///////////////////////////////////////////////////////////////////////////////



    @FXML
    void setDeletePatient(ActionEvent event) {

        ObservableList<Patients> selectedIndices = PatientTable.getSelectionModel().getSelectedItems();
        ArrayList<Patients> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
            PatientTable.getItems().remove(row);
            deleteRow(row);
            PatientTable.refresh();});
    }

    private void deleteRow(Patients row) {
        // TODO Auto-generated method stub

        try {
           // System.out.println("delete from Patients where PID="+row.getPID()+ ";");
            connectDB();
            ExecuteStatement("delete from Patients where PID="+row.getPID()+ ";");
            con.close();
           // System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        }
        catch(SQLException s) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error !!!");
            error.showAndWait();
        }
    }
    @FXML
    public void PatScene(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("patientOptions.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        PID.setCellValueFactory(new PropertyValueFactory<Patients,Integer>("PID"));
        Pname.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pname"));
        Phone.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pphone"));
        Pgender.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pgender"));
        Pemail.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pemail"));
        Birthdate.setCellValueFactory(new PropertyValueFactory<Patients, String>("Birthdate"));

        try {

             getDataPatient(1);
            PatientTable.setItems(list);

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SearchID.setOnAction((ActionEvent e)->{
            PatientTable.setItems(search);
            try {
                getDataPatient(2);
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException(e1);
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        });
        refresh.setOnAction((ActionEvent e)->{
            try {
                 getDataPatient(1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            PatientTable.setItems(list);
        });

    }
    ObservableList<Patients> list = FXCollections.observableArrayList();
    ObservableList<Patients> search = FXCollections.observableArrayList();


    public void getDataPatient(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();


        if(i==1) {
            list.clear();
            try {
                SQL = "select * from Patients";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    list.add(new Patients(Integer.valueOf(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate()
                    ));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if(i==2){
            try {
                search.clear();
                SQL = "select * from Patients where PID="+idtext.getText();
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new Patients(Integer.valueOf(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate()
                    ));
                }
            } catch (Exception e) {

            }
        }
        }


    private void connectDB() throws ClassNotFoundException, SQLException {
        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(dbURL, p);
    }


    @FXML
    public void HomeReturn(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }

}




