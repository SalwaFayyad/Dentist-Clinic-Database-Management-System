package com.example.dataproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class MedicalState {

    private int PID;
    private String Pmedicalstate;
    private LocalDate DateOfLastVisit;
    private String PatientName;



    public MedicalState(int PID, String PatientName, String Pmedicalstate, LocalDate DateOfLastVisit) {

        this.PID = PID;
        this.Pmedicalstate = Pmedicalstate;
        this.DateOfLastVisit = DateOfLastVisit;
        this.PatientName=PatientName;



    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public LocalDate getDateOfLastVisit() {
        return DateOfLastVisit;
    }

    public void setDateOfLastVisit(LocalDate dateOfLastVisit) {
        DateOfLastVisit = dateOfLastVisit;
    }

    public String getPmedicalstate() {
        return Pmedicalstate;
    }

    public void setPmedicalstate(String pmedicalstate) {
        Pmedicalstate = pmedicalstate;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }
}
