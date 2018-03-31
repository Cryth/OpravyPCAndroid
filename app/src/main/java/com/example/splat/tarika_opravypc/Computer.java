package com.example.splat.tarika_opravypc;

/**
 * Created by splat on 31.3.2018.
 */

public class Computer {
    private long ID;
    private long cID;
    private String Znacka;
    private String Model;

    public Computer(long ID, long cID, String znacka, String model) {
        this.ID = ID;
        this.cID = cID;
        Znacka = znacka;
        Model = model;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getcID() {
        return cID;
    }

    public void setcID(long cID) {
        this.cID = cID;
    }

    public String getZnacka() {
        return Znacka;
    }

    public void setZnacka(String znacka) {
        Znacka = znacka;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
