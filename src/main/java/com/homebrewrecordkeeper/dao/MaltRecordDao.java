package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordDao {
    int addMaltRecord(MaltRecordEntity maltRecordEntity);
    void deleteMaltRecord(String id);
    void updateMaltRecord(String id, MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(String id);
    List<MaltRecordEntity> getAll();
}
