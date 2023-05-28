package com.example.dataproject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private LocalDate Adate;
    private LocalTime Atime;
    private String Pname;
    private int PID;


    public Appointment() {

    }

    public Appointment(LocalDate adate, LocalTime atime, String pname, int PID) {
        this.Adate = adate;
        this.Atime = atime;
        this.Pname = pname;
        this.PID = PID;
    }

    public LocalDate getAdate() {
        return Adate;
    }

    public void setAdate(LocalDate adate) {
        Adate = adate;
    }

    public LocalTime getAtime() {
        return Atime;
    }

    public void setAtime(LocalTime atime) {
        Atime = atime;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }
}