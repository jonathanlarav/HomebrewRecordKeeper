package com.homebrewrecordkeeper.services;

import java.util.List;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:homebrewRecordKeeper-servlet.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class MaltRecordRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private MaltRecordRepository _maltRecordRepository;

    private SessionFactory sessionFactory;

    @Before
    public void setupTest()
    {
        sessionFactory = _maltRecordRepository.getSessionFactory();
    }
    @Test
    public void addMaltRecordTest()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        _maltRecordRepository.addMaltRecord(newMaltRecordEntity);
        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();
        assertThat(maltRecordEntityList, hasItem(newMaltRecordEntity));
        assertThat(newMaltRecordEntity.getId(), is(not(0)));
    }
    @Test
    public void updateMaltRecordTest()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        _maltRecordRepository.addMaltRecord(newMaltRecordEntity);

        newMaltRecordEntity.setName("test2");

        _maltRecordRepository.updateMaltRecord(newMaltRecordEntity);

        MaltRecordEntity maltRecordEntity = (MaltRecordEntity)sessionFactory.getCurrentSession()
            .createQuery("from MaltRecordEntity where id = ?").setParameter(0,newMaltRecordEntity.getId()).uniqueResult();
        assertThat(maltRecordEntity.getName(), equalTo("test2"));
    }

    @Test
    public void deleteMaltRecordTest()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        _maltRecordRepository.addMaltRecord(newMaltRecordEntity);
        boolean result = _maltRecordRepository.deleteMaltRecord(newMaltRecordEntity.getId());

        assertThat(result, is(true));

        MaltRecordEntity maltRecordEntity = (MaltRecordEntity)sessionFactory.getCurrentSession()
            .createQuery("from MaltRecordEntity where id = ?").setParameter(0,newMaltRecordEntity.getId()).uniqueResult();


        assertThat(maltRecordEntity,IsNull.nullValue());
    }

    @Test
    public void getAllMaltRecordTest()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        MaltRecordEntity newMaltRecordEntity2 = new MaltRecordEntity("test2",2,"test2","test2");
        _maltRecordRepository.addMaltRecord(newMaltRecordEntity);
        _maltRecordRepository.addMaltRecord(newMaltRecordEntity2);

        List<MaltRecordEntity> allMaltRecords = _maltRecordRepository.getAll();

        assertThat(allMaltRecords, contains(newMaltRecordEntity, newMaltRecordEntity2));

    }
}
