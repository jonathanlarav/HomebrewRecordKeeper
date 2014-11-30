package com.homebrewrecordkeeper.service.integration;

import com.homebrewrecordkeeper.config.ApplicationConfig;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
import com.homebrewrecordkeeper.service.HopRecordService;
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
public class HopRecordServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private HopRecordService hopRecordService;

    @SuppressWarnings("unchecked")
    @Test
    public void addHopRecordTest()
    {
        createHopRecord();
    }

    @Test
    public void updateHopRecordTest()
    {
        HopRecordEntity existingHopRecord = hopRecordService.getHopRecordById(1);

        existingHopRecord.setType("New Hop");
        existingHopRecord.setAmount(6d);

        HopRecordEntity returnedHopRecord = hopRecordService.updateHopRecord(existingHopRecord, existingHopRecord.getId());
        HopRecordEntity modifiedHopRecord = hopRecordService.getHopRecordById(1);

        assertThat(returnedHopRecord.getType(),equalTo("New Hop"));
        assertThat(returnedHopRecord.getAmount(),equalTo(6d));
        assertThat(modifiedHopRecord.getType(),equalTo("New Hop"));
        assertThat(modifiedHopRecord.getAmount(),equalTo(6d));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteHopRecordTest()
    {
        HopRecordEntity existingHopRecord = createHopRecord();

        boolean result = hopRecordService.deleteHopRecord(existingHopRecord.getId());

        List<HopRecordEntity> hopRecordEntityList = hopRecordService.getAll();

        assertThat(result,equalTo(true));
        assertThat(hopRecordEntityList,not(hasItem(existingHopRecord)));
    }
    @Test
    public void getAllHopRecordsTest()
    {
        List<HopRecordEntity> hopRecordEntityList = hopRecordService.getAll();
        assertThat(hopRecordEntityList,hasSize(2));
        assertThat(hopRecordEntityList.get(0).getType(),equalTo("Perle"));
    }
    @Test
    public void getHopRecordByIdTest()
    {
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(1);
        assertThat(hopRecordEntity.getId(),equalTo(1));
    }
    @Test
    public void getNotExistingHopRecordByIdTest()
    {
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(20);
        assertThat(hopRecordEntity,is(equalTo(null)));
    }
    @SuppressWarnings("unchecked")
    private HopRecordEntity createHopRecord()
    {
        HopRecordEntity newHopRecordEntity = new HopRecordEntity(5,"oz",60,"Cascade",5);
        HopRecordEntity createdHopRecord = hopRecordService.addHopRecord(newHopRecordEntity);
        List<HopRecordEntity> hopRecordEntityList = hopRecordService.getAll();
        assertThat(createdHopRecord.getId(),not(equalTo(0)));
        assertThat(hopRecordEntityList,hasItems(createdHopRecord));
        return newHopRecordEntity;
    }
}
