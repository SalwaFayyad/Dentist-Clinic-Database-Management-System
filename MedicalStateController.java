package com.example.dataproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class MedicalStateController  implements Initializable {

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;


    @FXML
    private TableColumn<MedicalState, Integer> pid;

    @FXML
    private TableView<MedicalState> medicalTable;

    @FXML
    private TableColumn<MedicalState, String> medicalstate;

    @FXML
    private TableColumn<MedicalState, LocalDate> Date;
    @FXML
    private TableColumn<MedicalState, String> Name;
    @FXML
    private TextField pidText;

    @FXML
    private Button  searchbtn;
    @FXML
    private Button  refreshbtn;

    @FXML
    void setDeletePatient(ActionEvent event) {

        ObservableList<MedicalState> selectedIndices = medicalTable.getSelectionModel().getSelectedItems();
        ArrayList<MedicalState> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
            medicalTable.getItems().remove(row);
            deleteRow(row);
            medicalTable.refresh();
        });
    }

    private void deleteRow(MedicalState row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from MedicalState where PID=" + row.getPID() + ";");
            connectDB();
            ExecuteStatement("delete from MedicalState where PID=" + row.getPID() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

        pid.setCellValueFactory(new PropertyValueFactory<MedicalState, Integer>("PID"));
        Name.setCellValueFactory(new PropertyValueFactory<MedicalState, String>("PatientName"));
        medicalstate.setCellValueFactory(new PropertyValueFactory<MedicalState, String>("Pmedicalstate"));
        Date.setCellValueFactory(new PropertyValueFactory<MedicalState, LocalDate>("DateOfLastVisit"));

        try {

            list = getDataMedical(1);
            medicalTable.setItems(list);

            // connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        searchbtn.setOnAction((ActionEvent e1)-> {
            ObservableList<MedicalState> search = FXCollections.observableArrayList();
            medicalTable.setItems(search);
            try {
                medicalTable.setItems(getDataMedical(2));
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        refreshbtn.setOnAction((ActionEvent e)->{

            try {

                list = getDataMedical(1);
                medicalTable.setItems(list);

                // connectDB();
            } catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
    }

    ObservableList<MedicalState> list = FXCollections.observableArrayList();
    ObservableList<MedicalState> searchCost = FXCollections.observableArrayList();

    public ObservableList<MedicalState> getDataMedical(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if (i == 1) {
            try {
                list.clear();
                SQL = "select * from MedicalState";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    list.add(new MedicalState(Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate()));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            return list;
        }
        if(i==2){
            searchCost.clear();

            try{
            SQL = "select * from MedicalState  where pid="+Integer.valueOf(pidText.getText())+";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                searchCost.add(new MedicalState(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate()));
            }
        } catch (Exception e2) {
            // TODO: handle exception
        }
        return searchCost;
        }
        return list;
    }



    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        }
        catch(SQLException s) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("An Error Occurred!!! ");

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
