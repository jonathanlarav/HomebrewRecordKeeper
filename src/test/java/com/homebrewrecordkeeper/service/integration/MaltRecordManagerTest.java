package com.homebrewrecordkeeper.service.integration;

import com.homebrewrecordkeeper.config.ApplicationConfig;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager="transactionManager")
public class MaltRecordManagerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private MaltRecordManager maltRecordManager;

    @SuppressWarnings("unchecked")
    @Test
    public void addMaltRecordTest()
    {
        createMaltRecord();
    }

    @Test
    public void updateMaltRecordTest()
    {
        MaltRecordEntity existingMaltRecord = maltRecordManager.getMaltRecordById(1);

        existingMaltRecord.setName("New Malt");
        existingMaltRecord.setType("Grains");

        MaltRecordEntity returnedMaltRecord = maltRecordManager.updateMaltRecord(existingMaltRecord, existingMaltRecord.getId());
        MaltRecordEntity modifiedMaltRecord = maltRecordManager.getMaltRecordById(1);

        assertThat(returnedMaltRecord.getName(),equalTo("New Malt"));
        assertThat(returnedMaltRecord.getType(),equalTo("Grains"));
        assertThat(modifiedMaltRecord.getName(),equalTo("New Malt"));
        assertThat(modifiedMaltRecord.getType(),equalTo("Grains"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteMaltRecordTest()
    {
        MaltRecordEntity existingMaltRecord = createMaltRecord();

        boolean result = maltRecordManager.deleteMaltRecord(existingMaltRecord.getId());

        List<MaltRecordEntity> maltRecordEntityList = maltRecordManager.getAll();

        assertThat(result,equalTo(true));
        assertThat(maltRecordEntityList,not(hasItem(existingMaltRecord)));
    }
    @Test
    public void getAllMaltRecordsTest()
    {
        List<MaltRecordEntity> maltRecordEntityList = maltRecordManager.getAll();
        assertThat(maltRecordEntityList.size(),equalTo(2));
        assertThat(maltRecordEntityList.get(0).getName(),equalTo("Muntons amber malt extract"));
    }
    @Test
    public void getMaltRecordByIdTest()
    {
        MaltRecordEntity maltRecordEntity = maltRecordManager.getMaltRecordById(1);
        assertThat(maltRecordEntity.getId(),equalTo(1));
    }
    @Test
    public void getNotExistingMaltRecordByIdTest()
    {
        MaltRecordEntity maltRecordEntity = maltRecordManager.getMaltRecordById(20);
        assertThat(maltRecordEntity,is(equalTo(null)));
    }
    @SuppressWarnings("unchecked")
    private MaltRecordEntity createMaltRecord()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        MaltRecordEntity createdMaltRecord = maltRecordManager.addMaltRecord(newMaltRecordEntity);
        List<MaltRecordEntity> maltRecordEntityList = maltRecordManager.getAll();
        assertThat(createdMaltRecord.getId(),not(equalTo(0)));
        assertThat(maltRecordEntityList,hasItems(createdMaltRecord));
        return newMaltRecordEntity;
    }
}
