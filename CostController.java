package com.example.dataproject;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.*;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javafx.collections.*;
import javafx.stage.Stage;

import java.net.*;


public class CostController implements Initializable{

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;



    @FXML
    private TextField TreCost;
    @FXML
    private TextField payCost;
    @FXML
    private TextField SumTre;
    @FXML
    private TextField SumPay;
    @FXML
    private TableColumn<Cost, Integer> PayCost;

    @FXML
    private TableColumn<Cost, Integer> TreatmentCost;

    @FXML
    private TableView<Cost> costTable;

    @FXML
    private TableColumn<Cost, LocalDate> date;

    @FXML
    private TableColumn<Cost, Integer> pid;

    @FXML
    private TableColumn<Cost, String> PName;
    @FXML
    private Button saveAdd;
    @FXML
    private TextField ID;

    @FXML
    private TextField Name;

    @FXML
    private DatePicker Date;
    @FXML
    private Button Search;

    @FXML
    private TableColumn<Cost, Integer> PayCost1;

    @FXML
    private TableColumn<Cost, Integer> TreatmentCost1;

    @FXML
    private TableView<Cost> costTable1;

    @FXML
    private TableColumn<Cost, LocalDate> date1;

    @FXML
    private TableColumn<Cost, Integer> pid1;

    @FXML
    private TableColumn<Cost, String> PName1;
    int counter = 1;


    private ObservableList<Cost> dataList; // to add data into the table view

    @FXML
    void setDeletePatient(ActionEvent event) {

        ObservableList<Cost> selectedIndices = costTable.getSelectionModel().getSelectedItems();
        ArrayList<Cost> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
            costTable.getItems().remove(row);
            deleteRow(row);
            costTable.refresh();});
    }

    private void deleteRow(Cost row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from cost where PID="+row.getPID()+ ";");
            connectDB();
            ExecuteStatement("delete from cost where PID="+row.getPID()+ ";");
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
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }
    }


    public void ExecuteStatement_ADD(String SQL, Cost c) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
            list.add(c);

        }
        catch(SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        pid.setCellValueFactory(new PropertyValueFactory<Cost,Integer>("PID"));
        PName.setCellValueFactory(new PropertyValueFactory<Cost, String>("pName"));
        date.setCellValueFactory(new PropertyValueFactory<Cost, LocalDate>("Date_of_visit"));
        TreatmentCost.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("treatment_cost"));
        PayCost.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("pay_cost"));

        pid1.setCellValueFactory(new PropertyValueFactory<Cost,Integer>("PID"));
        PName1.setCellValueFactory(new PropertyValueFactory<Cost, String>("pName"));
        date1.setCellValueFactory(new PropertyValueFactory<Cost, LocalDate>("Date_of_visit"));
        TreatmentCost1.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("treatment_cost"));
        PayCost1.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("pay_cost"));

        try {

            dataList = getCost();
            costTable.setItems(dataList);

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final LocalDate[] date = new LocalDate[1];
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // get the date picker value
                date[0] =Date.getValue();
                date[0] =Date.getValue();

            }
        };
        // show week numbers
        Date.setShowWeekNumbers(true);

        // when datePicker is pressed
        Date.setOnAction(event);


        saveAdd.setOnAction((ActionEvent e) -> {
            Cost c;
            for(Cost c1:list){
                if(c1.getPID()==Integer.valueOf(ID.getText())) {
                    try {
                        if (c1.getPName().equalsIgnoreCase(Name.getText())) {
                            c = new Cost(Integer.valueOf(ID.getText()), Name.getText(), date[0], Integer.valueOf(TreCost.getText()), Integer.valueOf(payCost.getText()));
                            insertData(date[0], c);
                        } else {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setHeaderText("Patient's ID Doesn't match Patient name ");
                            error.showAndWait();
                        }
                    }
                    catch(Exception e1){

                        e1.printStackTrace();
                    }
                }
                else {
                    c = new Cost(Integer.valueOf(ID.getText()), Name.getText(), date[0], Integer.valueOf(TreCost.getText()), Integer.valueOf(payCost.getText()));
                    insertData(date[0],c);
                    break;
                }
            }

        });

        Search.setOnAction((ActionEvent e) -> {
            ObservableList<Cost> rows = FXCollections.observableArrayList();

            costTable1.setItems(rows);
            try {
                costTable1.setItems(getDataCostSearch());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String SQL1="select SUM(treatment_cost) As Sum_tre  from cost  where pid="+Integer.valueOf(ID.getText());

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL1);
                while (rs.next()) {
                    SumTre.setText(rs.getString("Sum_tre"));
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            String SQL2="select SUM(pay_cost) As Sum_Pay  from cost  where pid="+Integer.valueOf(ID.getText());

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL2);
                while (rs.next()) {
                    SumPay.setText(rs.getString("Sum_Pay"));
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }


        });

    }
    public ObservableList<Cost> getDataCostSearch() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        searchlist.clear();

        try {
            SQL = "select * from cost where PID ="+Integer.valueOf(ID.getText());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                searchlist.add(new Cost( Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        Integer.parseInt(rs.getString(4)),
                        Integer.parseInt(rs.getString(5))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchlist;
    }


    public void insertData(LocalDate date,Cost c){

        try {
            connectDB();
            String Sql = "insert into cost values(" + Integer.valueOf(ID.getText()) + ",'" + Name.getText() + "','" + date + "'," + Integer.valueOf(TreCost.getText()) + "," + Integer.valueOf(payCost.getText()) + ");";
            ExecuteStatement_ADD(Sql, c);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    ObservableList<Cost> searchlist = FXCollections.observableArrayList();

    ObservableList<Cost> list = FXCollections.observableArrayList();

    public ObservableList<Cost> getCost() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        try {
            SQL = "select * from cost";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Cost(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        Integer.parseInt(rs.getString(4)),
                        Integer.parseInt(rs.getString(5))
                ));
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