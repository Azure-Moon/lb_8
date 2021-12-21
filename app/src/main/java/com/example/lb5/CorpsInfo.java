package com.example.lb5;

import java.util.UUID;

public class CorpsInfo {

    private int id;
    private String CorpName;
    public UUID ID_;

    CorpsInfo(int id, String CorpName)
    {
        this.id = id;
        this.CorpName = CorpName;
        ID_ = UUID.randomUUID();
    }

    CorpsInfo()
    {

    }

    public CorpsInfo(String string, String string1, int anInt) {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorpName()
    {
        return CorpName;
    }

    public void SetCorpName(String CorpName)
    {
        this.CorpName = CorpName;
    }

    public UUID getID_() { return ID_; }

    public void setID_() { this.ID_ = ID_; }

}
