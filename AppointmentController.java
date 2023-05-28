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

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;
    //////////////////////////////
    @FXML
    private TableColumn<Appointment, LocalDate> Adate;

    @FXML
    private TableView<Appointment> AppTable;

    @FXML
    private TableColumn<Appointment, LocalTime> Atime;

    @FXML
    private TableColumn<Appointment, Integer> PID;

    @FXML
    private TextField PIDText;

    @FXML
    private TableColumn<Appointment, String > Pname;


    @FXML
    private TextField PnameText;

    @FXML
    private Button addButton;

    @FXML
    private Button refreshButton;
    @FXML
    private Button searchDateButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button searchButton;

    @FXML
    private DatePicker datePick;
    @FXML
    private Button deleteButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField timeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Adate.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("Adate"));
        Atime.setCellValueFactory(new PropertyValueFactory<Appointment,LocalTime>("Atime"));
        Pname.setCellValueFactory(new PropertyValueFactory<Appointment,String>("Pname"));
        PID.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("PID"));


        try {

            list = getDataAppointment(1);
            AppTable.setItems(list);

            final LocalDate[] date = new LocalDate[1];
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    // get the date picker value
                    date[0] =datePick.getValue();

                    // get the selected date
                    System.out.println(date[0]);
                }
            };
            datePick.setOnAction(event);

            addButton.setOnAction((ActionEvent e) -> {
                Appointment A;
                for(Appointment a1:list){
                    if(a1.getPID()==Integer.valueOf(PIDText.getText())) {
                        try {
                            if (a1.getPname().equalsIgnoreCase(PnameText.getText())) {
                                if((LocalTime.parse(timeText.getText()) != a1.getAtime())&&(datePick.getValue()!=a1.getAdate())){
                                    A= new Appointment(date[0],LocalTime.parse(timeText.getText()),PnameText.getText(),Integer.parseInt(PIDText.getText()));
                                    list.add(A);
                                    insertData( date[0]);
                                    timeText.clear();
                                    PnameText.clear();
                                    PIDText.clear();

                                }else {
                                    Alert error = new Alert(Alert.AlertType.ERROR);
                                    error.setHeaderText(" Error In Insert Data");
                                    error.showAndWait();
                                }

                            } else {
                                Alert error = new Alert(Alert.AlertType.ERROR);
                                error.setHeaderText(" Error In Insert Data");
                                error.showAndWait();
                            }
                        }
                        catch(Exception e1){
                            e1.printStackTrace();
                        }
                    }else {
                        A= new Appointment(date[0],LocalTime.parse(timeText.getText()),PnameText.getText(),Integer.parseInt(PIDText.getText()));
                        list.add(A);
                        insertData( date[0]);
                        timeText.clear();
                        PnameText.clear();
                        PIDText.clear();
                        break;
                    }
                }
            });

            searchButton.setOnAction((ActionEvent e) -> {//search by id
                for(Appointment a :list){
                    if(a.getPID()== Integer.parseInt(PIDText.getText()))
                        PnameText.setText(a.getPname());
                }
                ObservableList<Appointment> re = FXCollections.observableArrayList();
                AppTable.setItems(re);
                try {
                    AppTable.setItems(getDataAppointment(2));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            });
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        searchDateButton.setOnAction((ActionEvent e) -> {

            ObservableList<Appointment> re = FXCollections.observableArrayList();

            AppTable.setItems(re);
            try {
                AppTable.setItems(getDataAppointment(3));
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
        updateButton.setOnAction((ActionEvent e)->{
            try {
                AppTable.setItems(getDataAppointment(4));
                timeText.clear();
                PnameText.clear();
                PIDText.clear();
                datePick.setValue(null);

            }catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteButton.setOnAction((ActionEvent e)->{
            try {
                AppTable.setItems(getDataAppointment(5));
                timeText.clear();
                PnameText.clear();
                PIDText.clear();
                datePick.setValue(null);



            }catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.setOnAction((ActionEvent e)->{

            try {

                Parent root_1 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene_3 = new Scene(root_1);
                Stage st = (Stage) (((Node) e.getSource()).getScene().getWindow());
                st.setScene(scene_3);
                st.show();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        refreshButton.setOnAction((ActionEvent e)->{
            try {
                list = getDataAppointment(1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            AppTable.setItems(list);
        });

    }
    public void insertData( LocalDate Adate ){
        String SQL;
        try {
            connectDB();
            System.out.println("insert into Appointments (Adate,Atime,Pname,PID) values('"+Adate+"','"+LocalTime.parse(timeText.getText())+"','"+PnameText.getText()+"',"+Integer.valueOf(PIDText.getText())+");");
            ExecuteStatement("insert into Appointments (Adate,Atime,Pname,PID) values('"+Adate+"','"+LocalTime.parse(timeText.getText())+"','"+PnameText.getText()+"',"+Integer.valueOf(PIDText.getText())+");");
            con.close();
        }catch (Exception e){
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
    ObservableList<Appointment> list = FXCollections.observableArrayList();
    ObservableList<Appointment> searchList = FXCollections.observableArrayList();
    ObservableList<Appointment> searchListByDate = FXCollections.observableArrayList();

    public ObservableList<Appointment> getDataAppointment(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        if(i==1){
            try {
                list.clear();
                SQL = "select * from Appointments";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    list.add(new Appointment(rs.getDate(1).toLocalDate(),
                            rs.getTime(2).toLocalTime(),
                            rs.getString(3),
                            Integer.valueOf(rs.getString(4))
                    ));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        else if(i==2){
            searchList.clear();
            try {
                SQL = "select * from Appointments where PID ="+Integer.valueOf(PIDText.getText());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchList.add(new Appointment(rs.getDate(1).toLocalDate(),
                            rs.getTime(2).toLocalTime(),
                            rs.getString(3),
                            Integer.valueOf(rs.getString(4))
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchList;
        }
        if(i==3) {
            searchListByDate.clear();
            try {
                SQL = "select * from Appointments where Adate = '" + datePick.getValue() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchListByDate.add(new Appointment(rs.getDate(1).toLocalDate(),
                            rs.getTime(2).toLocalTime(),
                            rs.getString(3),
                            Integer.valueOf(rs.getString(4))
                    ));

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchListByDate;
        }
        if(i==4) {
            try {
                for (Appointment ap : list) {
                    if (ap.getPID() == Integer.valueOf(PIDText.getText()))
                        PnameText.setText(ap.getPname());

                }

                if (timeText.getText() != null) {

                    SQL = "update Appointments set Atime='" + LocalTime.parse(timeText.getText()) + "' where PID=" + Integer.valueOf(PIDText.getText()) + " and Adate='" + datePick.getValue() + "'";
                    ExecuteStatement(SQL);
                    getDataAppointment(1);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }
        if(i==5){//delete by date and PID
            SQL = "delete from Appointments where PID="+Integer.valueOf(PIDText.getText())+" and Adate = '"+datePick.getValue()+"'";
            ExecuteStatement(SQL);
            getDataAppointment(1);
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
}