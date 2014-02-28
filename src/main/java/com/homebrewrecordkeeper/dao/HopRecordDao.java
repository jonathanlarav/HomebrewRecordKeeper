package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.HopRecordEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public interface HopRecordDao {
    HopRecordEntity addHopRecord(HopRecordEntity hopRecordEntity);
    HopRecordEntity updateHopRecord(HopRecordEntity hopRecordEntity);
    boolean deleteHopRecord(HopRecordEntity hopRecordEntity);
    List<HopRecordEntity> getAll();
    HopRecordEntity getHopRecordById(int id);
    SessionFactory getSessionFactory();
    void setSessionFactory(SessionFactory sessionFactory);
}
