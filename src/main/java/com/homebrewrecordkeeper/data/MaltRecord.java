package com.homebrewrecordkeeper.data;

public class MaltRecord {
    private String Name;
    private int Amount;
    private String Unit;
    private String Type;
    public MaltRecord(String name, int amount, String unit, String type)
    {
        Name = name;
        Amount = amount;
        Unit = unit;
        Type = type;
    }
}
