package com.example.dataproject;



public class Diagnosis {
    private int Did;
    private String Dtype;

    public Diagnosis(int Did, String Dtype) {
        this.Did = Did;
        this.Dtype = Dtype;

    }

    public int getDid() {
        return Did;
    }


    public void setDid(int Did) {
        Did = Did;
    }


    public String getDtype() {
        return Dtype;
    }


    public void setDtype(String Dtype) {
        Dtype = Dtype;
    }




}