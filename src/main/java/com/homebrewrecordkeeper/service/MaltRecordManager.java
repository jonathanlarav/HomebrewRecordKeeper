package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordManager {
    int addMaltRecord(MaltRecordEntity maltRecordEntity);
    void deleteMaltRecord(String id);
    void updateMaltRecord(String id, MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(String id);
    List<MaltRecordEntity> getAll();
}
