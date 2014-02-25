package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:homebrewRecordKeeper-servlet.xml")
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class MaltRecordManagerTest {
    @Test
    public void addMaltRecordTest()
    {
//        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl();
//        maltRecordManager.addMaltRecord(new MaltRecordEntity(null,"test1",2,"test1","test1"));
    }
}
