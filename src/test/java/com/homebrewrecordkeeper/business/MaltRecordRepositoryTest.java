package com.homebrewrecordkeeper.business;

import com.homebrewrecordkeeper.data.DbContext;
import com.homebrewrecordkeeper.data.MaltRecord;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MaltRecordRepositoryTest {
    @Test
    public void CreateMaltRecordInRepository()
    {
       DbContext dbContext = new DbFakeContext();
       int totalRecords = dbContext.getMaltRecords().size();
       MaltRecordRepository maltRecordRepository = new MaltRecordRepository(dbContext);

       MaltRecord maltRecord = new MaltRecord(
               "Muntons amber malt extract",
               2,
               "lbs",
               "dry"
       );
       maltRecordRepository.Add(maltRecord);
       assertEquals("The malt record was not added to the repository", totalRecords + 1, dbContext.getMaltRecords().size());
       assertTrue("The malt record is different in the repository", dbContext.getMaltRecords().contains(maltRecord));
    }
}
