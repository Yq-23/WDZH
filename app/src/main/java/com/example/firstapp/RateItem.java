package com.example.firstapp;

public class RateItem {
    public String curname;
    public float currate;
    public int id;

    public RateItem() {
        super();
        curname = "";
        currate = 0;
    }

    public RateItem(String curname, float currate) {
        super();
        this.curname = curname;
        this.currate = currate;
    }

    public String getCurname() {
        return curname;
    }

    public void setCurname(String curname) {
        this.curname = curname;
    }

    public float getCurrate() {
        return currate;
    }

    public void setCurrate(float currate) {
        this.currate = currate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
