package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.dao.HopRecordDao;
import com.homebrewrecordkeeper.entity.HopRecordEntity;

import java.util.List;

public interface HopRecordService {
    HopRecordEntity addHopRecord(HopRecordEntity hopRecordEntity);
    boolean deleteHopRecord(int id);
    HopRecordEntity updateHopRecord(HopRecordEntity hopRecordEntity, int id);
    HopRecordEntity getHopRecordById(int id);
    List<HopRecordEntity> getAll();
    void setHopRecordDao(HopRecordDao hopRecordDao);
    HopRecordDao getHopRecordDao();
}
