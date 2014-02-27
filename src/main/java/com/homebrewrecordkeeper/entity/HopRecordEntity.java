package com.homebrewrecordkeeper.entity;

import javax.persistence.*;

@Entity
@Table(name="hoprecord")
public class HopRecordEntity {
    @Id
    @SequenceGenerator(name = "ID_seq", sequenceName = "hoprecord_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ID_seq")
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private Integer Id;
    @Column(name="amount", nullable = false)
    private double Amount;
    @Column(name="unit", nullable = false)
    private String Unit;
    @Column(name="timeInMinutes", nullable = false)
    private int TimeInMinutes;
    @Column(name="type", nullable = false)
    private String Type;
    @Column(name="alphaAcid", nullable = true)
    private double AlphaAcid;

    public HopRecordEntity(double amount, String unit, int timeInMinutes, String type, double alphaAcid)
    {
        setAmount(amount);
        setUnit(unit);
        setTimeInMinutes(timeInMinutes);
        setType(type);
        setAlphaAcid(alphaAcid);
    }
    public HopRecordEntity()
    {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }


    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getTimeInMinutes() {
        return TimeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        TimeInMinutes = timeInMinutes;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getAlphaAcid() {
        return AlphaAcid;
    }

    public void setAlphaAcid(double alphaAcid) {
        AlphaAcid = alphaAcid;
    }
}
