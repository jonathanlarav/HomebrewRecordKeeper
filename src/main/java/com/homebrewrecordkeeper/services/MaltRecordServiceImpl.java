package com.homebrewrecordkeeper.services;

import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaltRecordServiceImpl implements MaltRecordService {
    @Autowired
    private MaltRecordRepository _maltRecordRepository;

    public MaltRecordServiceImpl(MaltRecordRepository mrd)
    {
        _maltRecordRepository = mrd;
    }
    public MaltRecordServiceImpl()
    {

    }

    @Override
    @Transactional
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return _maltRecordRepository.addMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public boolean deleteMaltRecord(int id) {
        return _maltRecordRepository.deleteMaltRecord(id);
    }

    @Override
    @Transactional
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity) {
        return _maltRecordRepository.updateMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public MaltRecordEntity getMaltRecordById(int id) {
        return _maltRecordRepository.getMaltRecordById(id);
    }

    @Override
    @Transactional
    public List<MaltRecordEntity> getAll() {
        return _maltRecordRepository.getAll();
    }

    public void setMaltRecordRepository(MaltRecordRepository maltRecordRepository) {
        this._maltRecordRepository = maltRecordRepository;
    }
    public MaltRecordRepository getMaltRecordRepository() {
        return this._maltRecordRepository;
    }
}
