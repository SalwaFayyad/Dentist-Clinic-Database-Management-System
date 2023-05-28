package com.example.dataproject;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.*;
import javafx.stage.*;
import javafx.event.*;
import java.sql.*;
import javafx.scene.*;
import java.util.*;
import javafx.collections.*;
import java.net.*;
public class controlTest {

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;

    @FXML
    public void PatientButton(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("patientTable.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }

    @FXML
    public void TreatmentButton(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("Treatment.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }
    @FXML
    public void DiagnosisButton(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("Diagnosis.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }
    @FXML
    public void PrescriptionMedication(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("Perscription_Medication.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @FXML
    public void costButton(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("Cost.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

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

    @FXML
    public void AppointmentsButton(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }
    @FXML
    public void Medical(ActionEvent event) {

        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("MedicalTest.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

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


}
