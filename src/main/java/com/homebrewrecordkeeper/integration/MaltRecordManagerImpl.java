package com.homebrewrecordkeeper.integration;

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

    public MaltRecordManagerImpl(MaltRecordDao mrd)
    {
        maltRecordDao = mrd;
    }
    public MaltRecordManagerImpl()
    {

    }

    @Override
    @Transactional
    public int addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return maltRecordDao.addMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public void deleteMaltRecord(String id) {
        maltRecordDao.deleteMaltRecord(id);
    }

    @Override
    @Transactional
    public void updateMaltRecord(String id, MaltRecordEntity maltRecordEntity) {
        maltRecordDao.updateMaltRecord(id,maltRecordEntity);
    }

    @Override
    @Transactional
    public MaltRecordEntity getMaltRecordById(String id) {
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
