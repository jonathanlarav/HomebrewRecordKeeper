package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordManager {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(String id);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(String id);
    List<MaltRecordEntity> getAll();
    void setMaltRecordDao(MaltRecordDao maltRecordDao);
    MaltRecordDao getMaltRecordDao();
}
