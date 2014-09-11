package com.homebrewrecordkeeper.unit;

import com.homebrewrecordkeeper.services.MaltRecordService;
import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.services.MaltRecordServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void deleteMaltRecordTest()
    {
        MaltRecordRepository maltRecordRepository = createNiceMock(MaltRecordRepository.class);
        final MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        newMaltRecordEntity.setId(1);

        expect(maltRecordRepository.deleteMaltRecord(1)).andReturn(true);
        replay(maltRecordRepository);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl(maltRecordRepository);
        boolean result = maltRecordService.deleteMaltRecord(1);

        assertThat(result, is(true));
    }

    @Test
    public void getAllMaltRecordTest()
    {
        MaltRecordRepository maltRecordRepository = createNiceMock(MaltRecordRepository.class);
        final MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        newMaltRecordEntity.setId(1);

        final MaltRecordEntity newMaltRecordEntity2 = new MaltRecordEntity("test2",2,"test2","test2");
        newMaltRecordEntity.setId(2);

        expect(maltRecordRepository.getAll()).andReturn(Arrays.asList(newMaltRecordEntity, newMaltRecordEntity2));
        replay(maltRecordRepository);

        MaltRecordService maltRecordService = new MaltRecordServiceImpl(maltRecordRepository);
        List<MaltRecordEntity> result = maltRecordService.getAll();

        assertThat(result,contains(newMaltRecordEntity, newMaltRecordEntity2));
    }
}
