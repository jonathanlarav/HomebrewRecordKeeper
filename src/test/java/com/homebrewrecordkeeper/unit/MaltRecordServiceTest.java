package com.homebrewrecordkeeper.unit;

import com.homebrewrecordkeeper.services.MaltRecordService;
import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.services.MaltRecordServiceImpl;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

public class MaltRecordServiceTest {
    @Test
    public void addMaltRecordTest()
    {
        MaltRecordRepository maltRecordRepository = createNiceMock(MaltRecordRepository.class);
        final MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        final MaltRecordEntity dbMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        dbMaltRecordEntity.setId(100);
        expect(maltRecordRepository.addMaltRecord(newMaltRecordEntity)).andReturn(dbMaltRecordEntity);

        replay(maltRecordRepository);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl(maltRecordRepository);
        int maltRecordId = maltRecordService.addMaltRecord(newMaltRecordEntity).getId();

        assertEquals(100,maltRecordId);
    }

    @Test
    public void updateMaltRecordTest()
    {
        MaltRecordRepository maltRecordRepository = createNiceMock(MaltRecordRepository.class);
        final MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        final MaltRecordEntity dbMaltRecordEntity = new MaltRecordEntity("test2",2,"test1","test1");

        expect(maltRecordRepository.updateMaltRecord(newMaltRecordEntity)).andReturn(dbMaltRecordEntity);
        replay(maltRecordRepository);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl(maltRecordRepository);
        MaltRecordEntity maltRecordEntity = maltRecordService.updateMaltRecord(newMaltRecordEntity);

        assertEquals(maltRecordEntity.getName(),"test2");
    }
}
