package com.homebrewrecordkeeper.business;

import com.homebrewrecordkeeper.data.DbContext;
import com.homebrewrecordkeeper.data.MaltRecord;

public class MaltRecordRepository {
    private DbContext db;

    public MaltRecordRepository(DbContext dbContext) {
        db = dbContext;
    }

    public void Add(MaltRecord maltRecord) {
        db.getMaltRecords().add(maltRecord);
    }
}
