package com.example.lb5;

public class FoundersInfo {
    private int id;
    private String FounderName;

    FoundersInfo(int id, String FounderName)
    {
        this.id = id;
        this.FounderName = FounderName;
    }

    FoundersInfo()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFounderName()
    {
        return FounderName;
    }

    public void SetFounderName(String FounderName)
    {
        this.FounderName = FounderName;
    }
}
