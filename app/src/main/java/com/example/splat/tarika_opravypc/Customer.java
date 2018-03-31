package com.example.splat.tarika_opravypc;

/**
 * Created by splat on 31.3.2018.
 */

public class Customer {
    private long ID;
    private String Meno;
    private String Email;

    public Customer(long ID, String meno, String email) {
        this.ID = ID;
        Meno = meno;
        Email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getMeno() {
        return Meno;
    }

    public void setMeno(String meno) {
        Meno = meno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
