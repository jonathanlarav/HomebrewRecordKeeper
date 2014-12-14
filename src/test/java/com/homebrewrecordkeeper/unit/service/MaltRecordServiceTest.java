package com.homebrewrecordkeeper.unit.service;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordService;
import com.homebrewrecordkeeper.service.MaltRecordServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

public class MaltRecordServiceTest {
    @Test
    public void addMaltRecordTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        MaltRecordEntity createdMaltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        createdMaltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.addMaltRecord(maltRecordEntity)).andReturn(createdMaltRecordEntity);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        MaltRecordEntity testedMaltRecordEntity = maltRecordService.addMaltRecord(maltRecordEntity);

        assertThat(testedMaltRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedMaltRecordEntity.getUnit(),equalTo("test2"));
        assertThat(testedMaltRecordEntity.getType(),equalTo("test3"));
    }
    @Test
    public void updateExistingMaltRecordTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordEntity maltRecordEntityChanged = new MaltRecordEntity("test1",2,"test4","test5");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(maltRecordEntity.getId())).andReturn(maltRecordEntity);
        expect(maltRecordDao.updateMaltRecord(maltRecordEntity)).andReturn(maltRecordEntityChanged);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        MaltRecordEntity testedMaltRecordEntity = maltRecordService.updateMaltRecord(maltRecordEntityChanged, maltRecordEntity.getId());

        assertThat(testedMaltRecordEntity.getUnit(), equalTo("test4"));
        assertThat(testedMaltRecordEntity.getType(), equalTo("test5"));
    }
    @Test
    public void deleteExistingMaltRecordTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(maltRecordEntity.getId())).andReturn(maltRecordEntity);
        expect(maltRecordDao.deleteMaltRecord(maltRecordEntity)).andReturn(true);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        boolean result = maltRecordService.deleteMaltRecord(maltRecordEntity.getId());

        assertThat(result,equalTo(true));
    }
    @Test
    public void getAllMaltRecordsTest()
    {
        final MaltRecordEntity test1 = new MaltRecordEntity("test1",1,"test1","test1");
        final MaltRecordEntity test2 = new MaltRecordEntity("test2",1,"test2","test2");
        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getAll()).andReturn(new ArrayList<>(Arrays.asList(test1,test2)));
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        List<MaltRecordEntity> maltRecordEntityList = maltRecordService.getAll();

        assertThat(maltRecordEntityList.get(0),equalTo(test1));
        assertThat(maltRecordEntityList.get(1),equalTo(test2));
    }
    @Test
    public void getExistingMaltRecordByIdTest()
    {
        final MaltRecordEntity test1 = new MaltRecordEntity("test1",1,"test1","test1");
        test1.setId(1);
        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(1)).andReturn(test1);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        MaltRecordEntity maltRecordEntity = maltRecordService.getMaltRecordById(1);

        assertThat(maltRecordEntity.getId(),equalTo(1));
        assertThat(maltRecordEntity.getName(),equalTo(test1.getName()));
        assertThat(maltRecordEntity.getType(),equalTo(test1.getType()));
        assertThat(maltRecordEntity.getAmount(),equalTo(test1.getAmount()));
    }
    @Test
    public void getNotExistingMaltRecordByIdTest()
    {
        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(1)).andReturn(null);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        MaltRecordEntity maltRecordEntity = maltRecordService.getMaltRecordById(1);

        assertThat(maltRecordEntity,equalTo(null));
    }
    @Test
    public void updateNotExistingMaltRecordTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(1)).andReturn(null);
        expect(maltRecordDao.updateMaltRecord(maltRecordEntity)).andReturn(maltRecordEntity);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        MaltRecordEntity testedMaltRecordEntity = maltRecordService.updateMaltRecord(maltRecordEntity, maltRecordEntity.getId());

        assertThat(testedMaltRecordEntity,equalTo(null));
    }
    @Test
    public void deleteNotExistingMaltRecordTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getMaltRecordById(maltRecordEntity.getId())).andReturn(null);
        expect(maltRecordDao.deleteMaltRecord(maltRecordEntity)).andReturn(false);
        replay(maltRecordDao);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl();
        maltRecordService.setMaltRecordDao(maltRecordDao);
        boolean result = maltRecordService.deleteMaltRecord(maltRecordEntity.getId());

        assertThat(result,equalTo(false));
    }
}
