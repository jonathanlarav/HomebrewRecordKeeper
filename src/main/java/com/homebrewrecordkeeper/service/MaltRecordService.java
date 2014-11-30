package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordService {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(int id);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity, int id);
    MaltRecordEntity getMaltRecordById(int id);
    List<MaltRecordEntity> getAll();
    void setMaltRecordDao(MaltRecordDao maltRecordDao);
    MaltRecordDao getMaltRecordDao();
}
