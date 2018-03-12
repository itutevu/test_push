package com.example.valkyzone.myapplication.model;

/**
 * Created by Valkyzone on 3/12/2018.
 */

public class Contact {
    private Boolean isMale;
    private String name;
    private String phonenum;

    public Contact(Boolean isMale,String name, String phonenum) {
        this.isMale = isMale;
        this.name = name;
        this.phonenum = phonenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
