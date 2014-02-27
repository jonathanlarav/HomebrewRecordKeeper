package com.homebrewrecordkeeper.service.unit;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
import com.homebrewrecordkeeper.service.MaltRecordManagerImpl;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;

public class MaltRecordManagerTest {
    @Test
    public void addMaltRecordTest()
    {
        final MaltRecordEntity maltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        maltRecordEntity.setId(100);

        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        expect(maltRecordDao.addMaltRecord(maltRecordEntity)).andReturn(maltRecordEntity);
        expect(maltRecordDao.getAll()).andReturn(new ArrayList<MaltRecordEntity>(){{add(maltRecordEntity);}});
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        MaltRecordEntity insertedMaltRecordEntity = maltRecordManager.addMaltRecord(maltRecordEntity);

        assertEquals(100,insertedMaltRecordEntity.getId());
    }
}
