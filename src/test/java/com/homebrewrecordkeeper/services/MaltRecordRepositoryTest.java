package com.homebrewrecordkeeper.services;

import com.homebrewrecordkeeper.repositories.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
        assertTrue(maltRecordEntityList.contains(newMaltRecordEntity));
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
        assertEquals(maltRecordEntity.getName(),"test2");
    }
}
