package com.example.dataproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.control.Alert.*;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.stage.Stage;

import java.net.*;

public class PatientController implements Initializable {

    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "1200430";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "clinic";
    private Connection con;
///////////////////////////////////////////////////////////////////////////////

    @FXML
    private Button addButton;

    @FXML
    private TextField ageText;

    @FXML
    private TextArea notes;
    @FXML
    private Button deleteButton;

    @FXML
    private Button addMedical;
    @FXML
    private CheckBox diabetesCheck;

    @FXML
    private TextField emailText;

    @FXML
    private RadioButton femaleRedio;

    @FXML
    private TextField idText;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private CheckBox pregnantCheck;

    @FXML
    private CheckBox pressureCheck;

    @FXML
    private CheckBox sensitiveCheck;

    @FXML
    private CheckBox mouthcancerCheck;
    @FXML
    private CheckBox Osteoporosis;

    @FXML
    private Button updateButton;
    @FXML
    private Button searchPatient;
    @FXML
    private DatePicker Birthday;
    @FXML
    private DatePicker DateOfLastVisit;

    @FXML
    private ComboBox DiaMenu;
    @FXML
    private ComboBox TreMenu;

    @FXML
    private Button addPM;

    public void insertData(Patients p , String gen ,LocalDate Bdate){
        String SQL;
        try {
            connectDB();
            ExecuteStatement_ADD(p,"insert into Patients (PID,Pname,Pphone,Pgender,Pemail,Birthdate) values('"+ idText.getText()+"','"+nameText.getText()+"','"+phoneText.getText()+"','"+gen+"','"+emailText.getText()+"','"+Bdate+"')");

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertIntoMedical(MedicalState m ,String medical, LocalDate date){
        String SQL;
        try {
            connectDB();
            SQL="insert into medicalstate (PID,PName,Pmedicalstate,DateOfLastVisit) values("+ idText.getText()+",'"+nameText.getText()+"','"+medical+"','"+date+"')";
            ExecuteStatement(SQL);

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void deleteRow(int id) {
        // TODO Auto-generated method stub

        try {
            connectDB();
            ExecuteStatement("delete from Patients where PID="+id+ ";");
            con.close();

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
            Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.CONFIRMATION);
                a.setContentText("OPERATION SUCSSESFULLY ");
                a.show();
                stmt.close();
        }
        catch(SQLException s) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("An Error Occurred!!! ");
            error.showAndWait();
        }
    }
    public void ExecuteStatement_ADD(Patients p, String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            list.add(p);
            Alert a = new Alert(AlertType.NONE);
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("ADDED SUCSSESFULLY ");
            a.show();
            stmt.close();

        }
        catch(SQLException s) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("An Error Occurred!!! ");
            error.showAndWait();
        }
    }


    public PatientController() throws IOException {
    }

    public void insertDataPerscription_Medication(){

        try {
            String SQL;
            int tid=0;
            connectDB();
            SQL = "select Treatment_id from Treatment where Ttype = '"+TreMenu.getValue()+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                tid = Integer.valueOf(rs.getString("Treatment_id"));
            }
            ExecuteStatement("insert into Perscription_Medication (PID,Pname,Dname,Treatment_id,Tname,lastDate,Notes) values("+ Integer.parseInt(idText.getText())+",'"+nameText.getText()+"','"+DiaMenu.getValue()+"',"+tid+",'"+TreMenu.getValue()+"','"+DateOfLastVisit.getValue()+"','"+notes.getText()+"')");

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final String[] medical = new String[1];
        EventHandler<ActionEvent> handler = e -> {

        };

        TreMenu.getItems().addAll("Metal Crown","Zironia","Pulptomy","All Ceramic", "Heavy Calculus", "Endodontic TRT","Non vital Pulptomy","Minor Calculus","Post and core","Scaling","Loose","Abscess","Pocket cleaning","Cyst","Granuloma","Ulcered Gingiva","Swajed Crown");
        DiaMenu.getItems().addAll("Swajed Crown","Nerves","Anodontia","Supernumerary Teeth","Motteled Teeth","Embedded Teeth","Dental Root Caries","Radicular Cyst","Periodontosis","Partial Loss Of Teeth","Retained Dental Root","Pulpitis","Cracked Tooth","Ankylosis of Tooth");

        pregnantCheck.setOnAction(handler);
        pressureCheck.setOnAction(handler);
        diabetesCheck.setOnAction(handler);
        sensitiveCheck.setOnAction(handler);


        ToggleGroup group = new ToggleGroup();
        maleRadio.setToggleGroup(group);
        femaleRedio.setToggleGroup(group);
        try {
            final String[] gen = new String[1];
            maleRadio.setOnAction(e -> {
                gen[0] =maleRadio.getText();
            });
            femaleRedio.setOnAction(e->{
                gen[0]= femaleRedio.getText();
            });

            final LocalDate[] Bdate = new LocalDate[1];
            final LocalDate[] DateLast = new LocalDate[1];
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    // get the date picker value
                    Bdate[0] =Birthday.getValue();
                    DateLast[0]=DateOfLastVisit.getValue();
                    // get the selected date
                }
            };

            // show week numbers
            Birthday.setShowWeekNumbers(true);
            DateOfLastVisit.setShowWeekNumbers(true);

            // when datePicker is pressed
            Birthday.setOnAction(event);
            DateOfLastVisit.setOnAction(event);

            list = getDataPatient();
            final String[] namePM = {null};
            final int[] pidPM = {0};
            addButton.setOnAction((ActionEvent e) -> {
                namePM[0] = nameText.getText();
                pidPM[0] = Integer.parseInt(idText.getText());
                Patients p;
                p=new Patients(Integer.valueOf(idText.getText()),nameText.getText(),phoneText.getText(),gen[0],emailText.getText(),Bdate[0]);
                Cost c=new Cost(Integer.valueOf(idText.getText()),nameText.getText(),null,0,0);
                insertData(p,gen[0], Bdate[0]);

                idText.clear();
                nameText.clear();
                phoneText.clear();
                emailText.clear();
                Birthday.setValue(null);
                maleRadio.setSelected(false);
                femaleRedio.setSelected(false);
            });
            MedicalArray=getDataMedical();

            addMedical.setOnAction((ActionEvent e) -> {
                if(pregnantCheck.isSelected()){
                    MedicalState m;
                    m=new MedicalState(Integer.valueOf(idText.getText()),nameText.getText(),"Pregnant",DateLast[0]);
                    insertIntoMedical(m,"Pregnant", DateLast[0]);

                }

                if(sensitiveCheck.isSelected()){
                    MedicalState m;
                    m=new MedicalState(pidPM[0], namePM[0],"Sensitive",DateLast[0]);
                    insertIntoMedical(m,"Sensitive", DateLast[0]);
                }

                if(diabetesCheck.isSelected()){
                    MedicalState m;
                    m=new MedicalState(Integer.valueOf(idText.getText()),nameText.getText(),"Diabetes",DateLast[0]);
                    insertIntoMedical(m,"Diabetes", DateLast[0]);
                }

                if(pressureCheck.isSelected()){
                    MedicalState m;
                    m=new MedicalState(Integer.valueOf(idText.getText()),nameText.getText(),"Pressure",DateLast[0]);
                    insertIntoMedical(m,"Pressure", DateLast[0]);
                }
                if(mouthcancerCheck.isSelected()){
                    MedicalState m;
                    m=new MedicalState(Integer.valueOf(idText.getText()),nameText.getText(),"Mouth Cancer",DateLast[0]);
                    insertIntoMedical(m,"Mouth Cancer", DateLast[0]);
                }
                if(Osteoporosis.isSelected()){
                    MedicalState m;
                    m=new MedicalState(Integer.valueOf(idText.getText()),nameText.getText(),"Osteoporosis",DateLast[0]);
                    insertIntoMedical(m,"Osteoporosis", DateLast[0]);
                }


            });

            addPM.setOnAction((ActionEvent e)-> {

                insertDataPerscription_Medication();

            });

            updateButton.setOnAction((ActionEvent e) -> {
                Patients p;

                p=new Patients(Integer.valueOf(idText.getText()),nameText.getText(),phoneText.getText(),gen[0],emailText.getText(),Bdate[0]);
                list.add(p);
                UpdateData(p,gen[0],Bdate[0]);
                idText.clear();
                nameText.clear();
                phoneText.clear();
                emailText.clear();
                Birthday.setValue(null);
                maleRadio.setSelected(false);
                femaleRedio.setSelected(false);
            });
            searchPatient.setOnAction((ActionEvent e) -> {
                ArrayList<Patients> rows = new ArrayList<>(list);
                for (Patients p : rows) {
                    if (Integer.valueOf(idText.getText())==p.getPID()) {
                        nameText.setText(p.getPname());
                        phoneText.setText(p.getPphone());
                        emailText.setText(p.getPemail());
                        if(p.getPgender().equalsIgnoreCase("female")){
                            femaleRedio.setSelected(true);
                        gen[0]="female";}
                        if(p.getPgender().equalsIgnoreCase("male")){
                            maleRadio.setSelected(true);
                            gen[0]="male";}
                        Birthday.setValue(p.getBirthdate());
                    }
                }
            } );
            deleteButton.setOnAction((ActionEvent e2) -> {
                ArrayList<Patients> rows2 = new ArrayList<>(list);
                for (Patients p : rows2) {
                    if (Integer.valueOf(idText.getText())==p.getPID() ) {
                        deleteRow(p.getPID());
                        list.remove(p);
                        idText.clear();
                    }
                }
            }  );



        } catch (ClassNotFoundException | SQLException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error !!!");
            error.showAndWait();        }
    }
    public void UpdateData(Patients rc , String gen , LocalDate Bdate){
        try {
            connectDB();
            ExecuteStatement("update patients set PName='"+nameText.getText()+"',pphone='"+phoneText.getText()+"',pgender='"+gen+"',pemail='"+emailText.getText()+"',birthdate='"+Bdate+"' where pid='"+ Integer.valueOf(idText.getText())+"';");
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    ObservableList<Patients> list = FXCollections.observableArrayList();
    ObservableList<MedicalState> MedicalArray = FXCollections.observableArrayList();

    public ObservableList<Patients> getDataPatient() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();


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

        }
        return list;
    }
    public ObservableList<MedicalState> getDataMedical() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        try {
            SQL = "select * from medicalstate";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                MedicalArray.add(new MedicalState(Integer.valueOf(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate()));
            }
        } catch (Exception e) {

        }
        return MedicalArray;
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

            Parent root_1 = FXMLLoader.load(getClass().getResource("patientTable.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }
}

