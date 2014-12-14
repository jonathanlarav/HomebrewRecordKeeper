package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.dao.HopRecordDao;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopRecordServiceImpl implements HopRecordService {

    @Autowired
    private HopRecordDao hopRecordDao;
    @Override
    public HopRecordEntity addHopRecord(HopRecordEntity hopRecordEntity) {
        return hopRecordDao.addHopRecord(hopRecordEntity);
    }

    @Override
    public boolean deleteHopRecord(int id) {
        HopRecordEntity hopRecordEntity = hopRecordDao.getHopRecordById(id);
        if(hopRecordEntity!=null)
            return hopRecordDao.deleteHopRecord(hopRecordEntity);
        else
            return false;
    }

    @Override
    public HopRecordEntity updateHopRecord(HopRecordEntity hopRecordEntity, int id) {
        HopRecordEntity hopRecordById = hopRecordDao.getHopRecordById(id);
        if(hopRecordById != null)
        {
            hopRecordById.setType(hopRecordEntity.getType());
            hopRecordById.setUnit(hopRecordEntity.getUnit());
            hopRecordById.setAmount(hopRecordEntity.getAmount());
            hopRecordById.setAlphaAcid(hopRecordEntity.getAlphaAcid());
            hopRecordById.setTimeInMinutes(hopRecordEntity.getTimeInMinutes());

            return hopRecordDao.updateHopRecord(hopRecordById);
        }
        return null;
    }

    @Override
    public HopRecordEntity getHopRecordById(int id) {
        return hopRecordDao.getHopRecordById(id);
    }

    @Override
    public List<HopRecordEntity> getAll() {
        return hopRecordDao.getAll();
    }

    @Override
    public void setHopRecordDao(HopRecordDao hrd) {
        hopRecordDao = hrd;
    }

    @Override
    public HopRecordDao getHopRecordDao() {
        return hopRecordDao;
    }
}
