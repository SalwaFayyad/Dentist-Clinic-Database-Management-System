package com.example.dataproject;

public class Treatment {

    private int TreatmentId;
    private int Did;
    private String Ttype;
    private double Treatment_Cost_Paid_Amount;



    public Treatment(int TreatmentId ,int Did,String Ttype,double Treatment_Cost_Paid_Amount) {
        this.TreatmentId = TreatmentId;
        this.Did = Did;
        this.Ttype = Ttype;
        this.Treatment_Cost_Paid_Amount = Treatment_Cost_Paid_Amount;


    }


    public int getTreatmentId() {
        return TreatmentId;
    }


    public void setTreatmentId(int TreatmentId) {
        TreatmentId = TreatmentId;
    }


    public int getDid() {
        return Did;
    }


    public void setDid(int Did) {
        Did = Did;
    }


    public String getTtype() {
        return Ttype;
    }


    public void setTtype(String Ttype) {
        Ttype = Ttype;
    }

    public double getTreatment_Cost_Paid_Amount() {
        return Treatment_Cost_Paid_Amount;
    }


    public void setTreatment_Cost_Paid_Amount(double Treatment_Cost_Paid_Amount) {
        Treatment_Cost_Paid_Amount = Treatment_Cost_Paid_Amount;
    }



    public void setRecords(String sg, int i) {
        switch (i) {
            case 1:
                TreatmentId =Integer.valueOf(sg) ;
                break;
            case 2:
                Did = Integer.valueOf(sg);
                break;
            case 3:
                Ttype = sg;
                break;
            case 4:
                Treatment_Cost_Paid_Amount =Double.valueOf(sg) ;
                break;

        }
    }
    public String getField(int i) {
        String sg=null;
        switch (i) {
            case 1:
                TreatmentId = Integer.valueOf(sg);
                break;
            case 2:
                Did = Integer.valueOf(sg);
                break;
            case 3:
                Ttype = sg;
                break;
            case 4:
                Treatment_Cost_Paid_Amount =Double.valueOf(sg) ;
                break;

        }
        return sg;
    }

}


