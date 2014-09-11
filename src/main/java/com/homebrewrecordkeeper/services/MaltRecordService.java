package com.homebrewrecordkeeper.services;

import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;

import java.util.List;

public interface MaltRecordService {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(int id);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(int id);
    List<MaltRecordEntity> getAll();
    void setMaltRecordRepository(MaltRecordRepository maltRecordRepository);
    MaltRecordRepository getMaltRecordRepository();
}
