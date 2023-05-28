
package com.example.dataproject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Perscription_Medication extends PatientController{
    ArrayList<Perscription_Medication> pm = new ArrayList<>();
    private int Pid;
    private String Pname;

    private String Dname;
    private int Treatment_id;

    private String Tname;
    private LocalDate lastDate;
    private String Notes;
    public Perscription_Medication() throws IOException {
        super();
    }
    public Perscription_Medication(int pid, String pname, String dname, int Treatment_id, String tname, LocalDate lastDate, String notes)throws IOException  {
        this.Pid = pid;
        this.Pname = pname;
        this.Dname = dname;
        this.Treatment_id = Treatment_id;
        this.Tname = tname;
        this.lastDate = lastDate;
        this.Notes = notes;
    }



    public ArrayList<Perscription_Medication> getPm() {
        return pm;
    }

    public void setPm(ArrayList<Perscription_Medication> pm) {
        this.pm = pm;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }



    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public int getTreatment_id() {
        return Treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        Treatment_id = treatment_id;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

}