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

    public MaltRecordManagerImpl(MaltRecordDao mrd) {
        maltRecordDao = mrd;
    }

    public MaltRecordManagerImpl() {

    }

    @Override
    @Transactional
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return maltRecordDao.addMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public boolean deleteMaltRecord(MaltRecordEntity maltRecordEntity) {
        return maltRecordDao.deleteMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity) {
        return maltRecordDao.updateMaltRecord(maltRecordEntity);
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
