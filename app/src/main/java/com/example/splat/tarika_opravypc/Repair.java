package com.example.splat.tarika_opravypc;

import java.util.Date;

/**
 * Created by splat on 31.3.2018.
 */

public class Repair {
    private long ID;
    private long coID;
    private Date Datum;
    private String Predmet;
    private String Popis;

    public Repair(long ID, long coID, Date datum, String predmet, String popis) {
        this.ID = ID;
        this.coID = coID;
        Datum = datum;
        Predmet = predmet;
        Popis = popis;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCoID() {
        return coID;
    }

    public void setCoID(long coID) {
        this.coID = coID;
    }

    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date datum) {
        Datum = datum;
    }

    public String getPredmet() {
        return Predmet;
    }

    public void setPredmet(String predmet) {
        Predmet = predmet;
    }

    public String getPopis() {
        return Popis;
    }

    public void setPopis(String popis) {
        Popis = popis;
    }
}
