package com.example.dataproject;


import java.time.LocalDate;

public class Patients {
    private int PID;
    private String Pname;
    private String Pgender;
    private String Pemail;
    private LocalDate Birthdate;
    private String Pmedicalstate;
    private String Pphone;
    private LocalDate DateOfLastVisit;



    public Patients(int PID, String Pname, String pPhone, String Pgender, String Pemail, LocalDate Birthdate) {
        this.Pemail = Pemail;
        this.Pname = Pname;
        this.Pgender = Pgender;
        this.Birthdate = Birthdate;
        this.PID = PID;
        this.Pphone= pPhone;
    }
    public Patients(int PID, String Pmedicalstate, LocalDate DateOfLastVisit) {

        this.PID = PID;
        this.Pmedicalstate = Pmedicalstate;
        this.DateOfLastVisit=DateOfLastVisit;
    }

    public String getPname() {
        return Pname;
    }


    public void setPname(String pname) {
        Pname = pname;
    }


    public String getPgender() {
        return Pgender;
    }


    public void setPgender(String pgender) {
        Pgender = pgender;
    }


    public String getPemail() {
        return Pemail;
    }


    public void setPemail(String pemail) {
        Pemail = pemail;
    }


    public LocalDate getBirthdate() {
        return Birthdate;
    }


    public void setBirthdate(LocalDate birthdate) {
        Birthdate = birthdate;
    }


    public int getPID() {
        return PID;
    }


    public void setPID(int PID) {
        PID = PID;
    }


    public String getPmedicalstate() {

        return Pmedicalstate;
    }


    public void setPmedicalstate(String pmedicalstate) {
        Pmedicalstate = pmedicalstate;
    }

    public void setPphone(String pphone) {
        Pphone = pphone;
    }

    public String getPphone() {
        return Pphone;
    }

    public LocalDate getDateOfLastVisit() {
        return DateOfLastVisit;
    }

    public void setDateOfLastVisit(LocalDate dateOfLastVisit) {
        DateOfLastVisit = dateOfLastVisit;
    }

}
