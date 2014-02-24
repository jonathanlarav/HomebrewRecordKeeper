package com.homebrewrecordkeeper.business;

import com.homebrewrecordkeeper.data.DbContext;
import com.homebrewrecordkeeper.data.MaltRecord;

import java.util.ArrayList;
import java.util.List;

public class DbFakeContext implements DbContext {
    private List<MaltRecord> MaltRecords;

    public DbFakeContext()
    {
        MaltRecords = new ArrayList<MaltRecord>();
    }

    @Override
    public List<MaltRecord> getMaltRecords()
    {
        return MaltRecords;
    }
}
