package com.example.dataproject;

import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.stage.Stage;

import java.net.*;
public class TreatmentController  implements Initializable  {


    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;


    @FXML
    private TableView<Treatment> TreatmentTable;

//    @FXML
//    private Label lbl;
//    @FXML
//    private Button btn = new Button();

    @FXML
    private TableColumn<Treatment, Integer > TreatmentId;

    @FXML
    private TableColumn<Treatment, Integer > Did;

    @FXML
    private TableColumn<Treatment, String > Ttype;

    @FXML
    private TableColumn<Treatment, Double> Treatment_Cost_Paid_Amount;

    @FXML
    private TextField Tid;
    @FXML
    private TextField Didd;
    @FXML
    private TextField TTY;
    @FXML
    private TextField TCPA;

    @FXML
    private Button Buttonadd;

    @FXML
    private Button Ret;


    @FXML
    void Buttonadd(ActionEvent event) throws SQLException, ClassNotFoundException {

    }
    public void insertData(Treatment rc , int  Did  , String Ttype,double Treatment_Cost_Paid_Amount ){
        String SQL;
        try {
            connectDB();
            ExecuteStatement("insert into Treatment (Treatment_Id,DID,Ttype,Treatment_Cost_Paid_Amount) values("+ Integer.valueOf(Tid.getText())+","+Integer.valueOf(Didd.getText())+",'"+TTY.getText()+"',"+Double.valueOf(TCPA.getText())+");");
            con.close();
        }catch (Exception e){
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
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }


    }

    private ObservableList<Treatment> dataList; // to add data into the table view



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("mm");

        TreatmentId.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("TreatmentId"));
        Did.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("Did"));
        Ttype.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Ttype"));
        Treatment_Cost_Paid_Amount.setCellValueFactory(new PropertyValueFactory<Treatment, Double>("Treatment_Cost_Paid_Amount"));



        try {
            //  System.out.println("llkj");

            dataList = getDataTreatment();
            // System.out.println("llkj11111");

            TreatmentTable.setItems(dataList);
            //  System.out.println("nbbnbnnb");


            // connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    ObservableList<Treatment> list = FXCollections.observableArrayList();
    public ObservableList<Treatment> getDataTreatment() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();



        try {
            SQL = "select * from Treatment";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Treatment(Integer.valueOf(rs.getString(1)),
                        Integer.valueOf(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4)));
            }
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please,Check SQL!!!");
            error.showAndWait();
        }
        return list;
    }
//////////////////////////////////////////////////////////ADD TREATMENT

    // list = getDataTreatment();
    //  Buttonadd.setOnAction((ActionEvent e) -> {


    //  });
    public void addTreatment(ActionEvent event) {

        try {
            Treatment T;
            T = new Treatment(Integer.valueOf(Tid.getText()), Integer.valueOf(Didd.getText()), TTY.getText(), Double.valueOf(TCPA.getText()));
            insertData(T,Integer.valueOf(Didd.getText()),TTY.getText(),Double.valueOf(TCPA.getText()));
            list.add(T);
            Tid.clear();
            Didd.clear();
            TTY.clear();
            TCPA.clear();

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please,Make sure the Data is Correct!!!");
            error.showAndWait();

        }
    }

//       ButtonDelete.setOnAction((ActionEvent e) -> {
//        Treatment T;
//        T = new Treatment(Integer.valueOf(Tid.getText()), Integer.valueOf(Didd.getText()), TTY.getText(), Double.valueOf(TCPA.getText()));
//        list.add(T);
//        Tid.clear();
//        Didd.clear();
//        TTY.clear();
//        TCPA.clear();
//
//    });
//





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