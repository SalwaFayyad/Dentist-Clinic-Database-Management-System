package com.example.dataproject;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.*;

import java.util.*;
import javafx.collections.*;
import java.net.*;
import javafx.event.ActionEvent;

public class DiagnosisController implements Initializable {

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;


    @FXML
    private TableView<Diagnosis> DiagnosisTable;

    @FXML
    private TableColumn<Diagnosis, Integer> Did;

    @FXML
    private TableColumn<Diagnosis, String > Dtype;

    @FXML
    void setDeletePatient(ActionEvent event) {

        ObservableList<Diagnosis> selectedIndices = DiagnosisTable.getSelectionModel().getSelectedItems();
        ArrayList<Diagnosis> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
            DiagnosisTable.getItems().remove(row);
            deleteRow(row);
            DiagnosisTable.refresh();});
    }

    private void deleteRow(Diagnosis row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from Diagnosis where did="+row.getDid()+ ";");
            connectDB();
            ExecuteStatement("delete from Diagnosis where did="+row.getDid()+ ";");
            con.close();
            System.out.println("Connection closed");

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
            error.setHeaderText("An Error Occurred!!! ");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("mm");

        Did.setCellValueFactory(new PropertyValueFactory<Diagnosis, Integer>("Did"));
        Dtype.setCellValueFactory(new PropertyValueFactory<Diagnosis, String>("Dtype"));


        try {

            list = getDataDiagnosis();
            DiagnosisTable.setItems(list);


            // connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void ExecuteStatement_ADD(String SQL, Diagnosis d) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            list.add(d);
            stmt.close();

        }
        catch(SQLException s) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("An Error Occurred!!! ");

        }
    }

    ObservableList<Diagnosis> list = FXCollections.observableArrayList();
    public ObservableList<Diagnosis> getDataDiagnosis () throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        try {
            SQL = "select * from Diagnosis";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Diagnosis(Integer.valueOf(rs.getString(1)),
                        rs.getString(2)));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
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
