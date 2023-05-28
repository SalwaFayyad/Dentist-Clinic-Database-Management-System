package com.example.dataproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PerscriptionMedicationController implements Initializable {
    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;
    ///////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableColumn<Perscription_Medication, String> Dname;
    @FXML
    private TableColumn<Perscription_Medication, Integer> Treatment_id;
    @FXML
    private TableColumn<Perscription_Medication, String> Notes;

    @FXML
    private TableColumn<Perscription_Medication, Integer> PID;

    @FXML
    private TableView<Perscription_Medication> Perscription_MedicationTable;

    @FXML
    private TableColumn<Perscription_Medication, String> Pname;

    @FXML
    private TableColumn<Perscription_Medication, String> Tname;

    @FXML
    private TableColumn<Perscription_Medication, LocalDate> lastDate;

    @FXML
    private Button refresh;


    @FXML
    private Button searchID;
    @FXML
    private TextField searchText;
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
    ObservableList<Perscription_Medication> PM = FXCollections.observableArrayList();
    ObservableList<Perscription_Medication> search = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PID.setCellValueFactory(new PropertyValueFactory<Perscription_Medication,Integer>("pid"));
        Pname.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Pname"));
        Dname.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Dname"));
        Treatment_id.setCellValueFactory(new PropertyValueFactory<Perscription_Medication,Integer>("Treatment_id"));
        Tname.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Tname"));
        lastDate.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, LocalDate>("lastDate"));
        Notes.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Notes"));
        try {
            getDataPerscription_Medication(1);
            Perscription_MedicationTable.setItems( PM);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        searchID.setOnAction((ActionEvent e)->{
            Perscription_MedicationTable.setItems(search);
            try {
                getDataPerscription_Medication(2);
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException(e1);
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        });
        refresh.setOnAction((ActionEvent e)->{
            try {
                getDataPerscription_Medication(1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Perscription_MedicationTable.setItems(PM);
        });
    }

    public void getDataPerscription_Medication(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if(i==1) {
            try {
                PM.clear();
                SQL = "select * from Perscription_Medication";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    PM.add(new Perscription_Medication(Integer.valueOf(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            Integer.parseInt(rs.getString(4)),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate(),
                            rs.getString(7)
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(i==2){
            try {
                search.clear();
                SQL = "select * from Perscription_Medication where PID="+searchText.getText();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new Perscription_Medication(Integer.valueOf(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            Integer.parseInt(rs.getString(4)),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate(),
                            rs.getString(7)
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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