package com.homebrewrecordkeeper.service.unit;

import com.homebrewrecordkeeper.dao.HopRecordDao;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
import com.homebrewrecordkeeper.service.HopRecordService;
import com.homebrewrecordkeeper.service.HopRecordServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class HopRecordServiceTest {

    @Test
    public void addHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        HopRecordEntity createdHopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        createdHopRecordEntity.setId(1);

        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.addHopRecord(hopRecordEntity)).andReturn(createdHopRecordEntity);
        replay(hopRecordDao);


        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        HopRecordEntity testedHopRecordEntity = hopRecordService.addHopRecord(hopRecordEntity);

        assertThat(testedHopRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedHopRecordEntity.getAmount(),equalTo(2d));
        assertThat(testedHopRecordEntity.getType(),equalTo("Cascade"));
    }
    @Test
    public void updateHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);
        HopRecordEntity changedHopRecordEntity = new HopRecordEntity(5,"oz",60,"Perle",5);
        changedHopRecordEntity.setId(1);

        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(hopRecordEntity.getId())).andReturn(hopRecordEntity);
        expect(hopRecordDao.updateHopRecord(hopRecordEntity)).andReturn(changedHopRecordEntity);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        HopRecordEntity testedHopRecordEntity = hopRecordService.updateHopRecord(changedHopRecordEntity, hopRecordEntity.getId());

        assertThat(testedHopRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedHopRecordEntity.getAmount(),equalTo(5d));
        assertThat(testedHopRecordEntity.getType(),equalTo("Perle"));
    }
    @Test
    public void deleteHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(hopRecordEntity.getId())).andReturn(hopRecordEntity);
        expect(hopRecordDao.deleteHopRecord(hopRecordEntity)).andReturn(true);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        boolean result = hopRecordService.deleteHopRecord(hopRecordEntity.getId());

        assertThat(result,equalTo(true));
    }
    @Test
    public void getAllHopRecordTest()
    {
        final HopRecordEntity test1 = new HopRecordEntity(2,"oz",60,"Cascade",5);
        final HopRecordEntity test2 = new HopRecordEntity(5,"oz",60,"Perle",5);
        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getAll()).andReturn(new ArrayList<>(Arrays.asList(test1, test2)));
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        List<HopRecordEntity> hopRecordEntityList = hopRecordService.getAll();

        assertThat(hopRecordEntityList.get(0),equalTo(test1));
        assertThat(hopRecordEntityList.get(1),equalTo(test2));
    }
    @Test
    public void getHopRecordByIdTest()
    {
        final HopRecordEntity test1 = new HopRecordEntity(2,"oz",60,"Cascade",5);
        test1.setId(1);
        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(1)).andReturn(test1);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(1);

        assertThat(hopRecordEntity.getId(),equalTo(1));
        assertThat(hopRecordEntity.getAlphaAcid(),equalTo(test1.getAlphaAcid()));
        assertThat(hopRecordEntity.getType(),equalTo(test1.getType()));
        assertThat(hopRecordEntity.getAmount(),equalTo(test1.getAmount()));
    }
    @Test
    public void getNotExistingHopRecordByIdTest()
    {
        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(1)).andReturn(null);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(1);

        assertThat(hopRecordEntity,equalTo(null));
    }
    @Test
    public void updateNotExistingHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(1)).andReturn(null);
        expect(hopRecordDao.updateHopRecord(hopRecordEntity)).andReturn(hopRecordEntity);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        HopRecordEntity testedHopRecordEntity = hopRecordService.updateHopRecord(hopRecordEntity, hopRecordEntity.getId());

        assertThat(testedHopRecordEntity,equalTo(null));
    }
    @Test
    public void deleteNotExistingHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordDao hopRecordDao = createNiceMock(HopRecordDao.class);
        expect(hopRecordDao.getHopRecordById(hopRecordEntity.getId())).andReturn(null);
        expect(hopRecordDao.deleteHopRecord(hopRecordEntity)).andReturn(false);
        replay(hopRecordDao);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordDao(hopRecordDao);
        boolean result = hopRecordService.deleteHopRecord(hopRecordEntity.getId());

        assertThat(result,equalTo(false));
    }
}
