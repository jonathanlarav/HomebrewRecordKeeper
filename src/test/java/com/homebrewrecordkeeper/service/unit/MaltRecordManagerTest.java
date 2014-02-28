package com.homebrewrecordkeeper.service.unit;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
import com.homebrewrecordkeeper.service.MaltRecordManagerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MaltRecordManagerTest {
    @Test
    public void addMaltRecordManagerTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        MaltRecordEntity createdMaltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        createdMaltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.addMaltRecord(maltRecordEntity)).andReturn(createdMaltRecordEntity);
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        MaltRecordEntity testedMaltRecordEntity = maltRecordManager.addMaltRecord(maltRecordEntity);

        assertThat(testedMaltRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedMaltRecordEntity.getUnit(),equalTo("test2"));
        assertThat(testedMaltRecordEntity.getType(),equalTo("test3"));
    }
    @Test
    public void updateExistingMaltRecordManagerTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.updateMaltRecord(maltRecordEntity)).andReturn(maltRecordEntity);
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        MaltRecordEntity testedMaltRecordEntity = maltRecordManager.updateMaltRecord(maltRecordEntity);

        assertThat(testedMaltRecordEntity.getUnit(), equalTo("test2"));
        assertThat(testedMaltRecordEntity.getType(), equalTo("test3"));
    }
    @Test
    public void deleteExistingMaltRecordManagerTest()
    {
        MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test2","test3");
        maltRecordEntity.setId(1);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.deleteMaltRecord(maltRecordEntity)).andReturn(true);
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        boolean result = maltRecordManager.deleteMaltRecord(maltRecordEntity);

        assertThat(result,equalTo(true));
    }
    @Test
    public void getAllMaltRecordsTest()
    {
        final MaltRecordEntity test1 = new MaltRecordEntity("test1",1,"test1","test1");
        final MaltRecordEntity test2 = new MaltRecordEntity("test2",1,"test2","test2");
        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.getAll()).andReturn(new ArrayList<MaltRecordEntity>(Arrays.asList(test1,test2)));
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        List<MaltRecordEntity> maltRecordEntityList = maltRecordManager.getAll();

        assertThat(maltRecordEntityList.get(0),equalTo(test1));
        assertThat(maltRecordEntityList.get(1),equalTo(test2));
    }
}
