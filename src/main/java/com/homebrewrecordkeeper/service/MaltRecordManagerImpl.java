package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaltRecordManagerImpl implements MaltRecordManager {
    @Autowired
    private MaltRecordDao maltRecordDao;

    @Override
    @Transactional
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return maltRecordDao.addMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public boolean deleteMaltRecord(int id) {
        MaltRecordEntity maltRecordEntity = maltRecordDao.getMaltRecordById(id);
        if(maltRecordEntity != null)
            return maltRecordDao.deleteMaltRecord(maltRecordEntity);
        else
            return false;
    }

    @Override
    @Transactional
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity, int id){

        MaltRecordEntity maltRecordEntityToModify = maltRecordDao.getMaltRecordById(id);
        if(maltRecordEntityToModify != null)
        {
            maltRecordEntityToModify.setUnit(maltRecordEntity.getUnit());
            maltRecordEntityToModify.setName(maltRecordEntity.getName());
            maltRecordEntityToModify.setType(maltRecordEntity.getType());
            maltRecordEntityToModify.setAmount(maltRecordEntity.getAmount());

            return maltRecordDao.updateMaltRecord(maltRecordEntityToModify);
        }

        return null;
    }

    @Override
    @Transactional
    public MaltRecordEntity getMaltRecordById(int id) {
        return maltRecordDao.getMaltRecordById(id);
    }

    @Override
    @Transactional
    public List<MaltRecordEntity> getAll() {
        return maltRecordDao.getAll();
    }

    public void setMaltRecordDao(MaltRecordDao maltRecordDao) {
        this.maltRecordDao = maltRecordDao;
    }

    public MaltRecordDao getMaltRecordDao() {
        return this.maltRecordDao;
    }
}
