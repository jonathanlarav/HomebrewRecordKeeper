package com.homebrewrecordkeeper.repositories;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public interface MaltRecordRepository {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(int id);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(int id);
    List<MaltRecordEntity> getAll();
    SessionFactory getSessionFactory();
}
