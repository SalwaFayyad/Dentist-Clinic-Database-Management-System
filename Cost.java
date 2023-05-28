package com.example.dataproject;

import java.time.LocalDate;

public class Cost {

    private int PID;
    private String PName;

    private LocalDate Date_of_visit;
    private int treatment_cost;
    private int pay_cost;


    public Cost() {
    }

    public Cost(int PID,String pName,LocalDate date_of_visit,int treatment_cost, int pay_cost) {

        this.PID = PID;
        this.PName =pName;
        this.Date_of_visit=date_of_visit;
        this.treatment_cost=treatment_cost;
        this.pay_cost=pay_cost;
    }

    public int getPID() {
        return PID;
    }
    public void setPID(int PID) {
        this.PID = PID;
    }

    public LocalDate getDate_of_visit() {
        return Date_of_visit;
    }

    public void setDate_of_visit(LocalDate date_of_visit) {
        Date_of_visit = date_of_visit;
    }

    public int getTreatment_cost() {
        return treatment_cost;
    }

    public void setTreatment_cost(int treatment_cost) {
        this.treatment_cost = treatment_cost;
    }

    public int getPay_cost() {
        return pay_cost;
    }

    public void setPay_cost(int pay_cost) {
        this.pay_cost = pay_cost;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

}
