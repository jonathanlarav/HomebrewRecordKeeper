package com.homebrewrecordkeeper.integration;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordManager {
    int addMaltRecord(MaltRecordEntity maltRecordEntity);
    void deleteMaltRecord(String id);
    void updateMaltRecord(String id, MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(String id);
    List<MaltRecordEntity> getAll();
    void setMaltRecordDao(MaltRecordDao maltRecordDao);
    MaltRecordDao getMaltRecordDao();
}
