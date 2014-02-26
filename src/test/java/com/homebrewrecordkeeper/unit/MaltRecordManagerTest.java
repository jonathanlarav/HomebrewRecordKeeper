package com.homebrewrecordkeeper.unit;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.integration.MaltRecordManager;
import com.homebrewrecordkeeper.integration.MaltRecordManagerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

public class MaltRecordManagerTest {
    @Test
    public void addMaltRecordTest()
    {
        MaltRecordDao maltRecordDao = createNiceMock(MaltRecordDao.class);
        final MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        expect(maltRecordDao.addMaltRecord(newMaltRecordEntity)).andReturn(100);
        expect(maltRecordDao.getAll()).andReturn(new ArrayList<MaltRecordEntity>(){{add(newMaltRecordEntity);}});
        replay(maltRecordDao);

        MaltRecordManager maltRecordManager = new MaltRecordManagerImpl(maltRecordDao);
        int maltRecordId = maltRecordManager.addMaltRecord(newMaltRecordEntity);

        assertEquals(100,maltRecordId);
    }
}
