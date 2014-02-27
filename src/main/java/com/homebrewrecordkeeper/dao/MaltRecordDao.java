package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public interface MaltRecordDao {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(String id);
    List<MaltRecordEntity> getAll();
    void setSessionFactory(SessionFactory sessionFactory);
    SessionFactory getSessionFactory();
}
