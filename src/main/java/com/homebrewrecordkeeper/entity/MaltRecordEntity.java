package com.homebrewrecordkeeper.entity;

import javax.persistence.*;

@Entity
@Table(name="maltrecord")
public class MaltRecordEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_seq")
    @SequenceGenerator(sequenceName = "maltrecord_id_seq", name = "ID_seq")
    @Column(name = "id", unique = true, nullable = false)
    @Id
    private String Id;
    @Column(name="name", unique = false, nullable = false)
    private String Name;
    @Column(name="amount", unique = false, nullable = false)
    private int Amount;
    @Column(name="unit", unique = false, nullable = false)
    private String Unit;
    @Column(name="type", unique = false, nullable = false)
    private String Type;

    public MaltRecordEntity(String id, String name, int amount, String unit, String type)
    {
        setId(id);
        setName(name);
        setAmount(amount);
        setUnit(unit);
        setType(type);
    }

    public MaltRecordEntity() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
