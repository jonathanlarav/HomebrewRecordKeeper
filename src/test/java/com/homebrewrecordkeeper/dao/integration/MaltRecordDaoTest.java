package com.homebrewrecordkeeper.dao.integration;

import com.homebrewrecordkeeper.dao.MaltRecordDao;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:homebrewRecordKeeper-servlet.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class MaltRecordDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private MaltRecordDao maltRecordDao;

    private SessionFactory sessionFactory;

    @Before
    public void setupTest()
    {
        sessionFactory = maltRecordDao.getSessionFactory();
    }
    @SuppressWarnings("unchecked")
    @Test
    public void addMaltRecordTest()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        maltRecordDao.addMaltRecord(newMaltRecordEntity);
        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();
        assertTrue(maltRecordEntityList.contains(newMaltRecordEntity));
    }
    @Test
    public void updateMaltRecordTest()
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id", 1));
        MaltRecordEntity existingMaltRecord = (MaltRecordEntity) criteria.uniqueResult();

        existingMaltRecord.setName("New Malt");
        existingMaltRecord.setType("Grains");

        maltRecordDao.updateMaltRecord(existingMaltRecord);

        MaltRecordEntity modifiedMaltRecord = (MaltRecordEntity) criteria.uniqueResult();

        assertEquals("New Malt",modifiedMaltRecord.getName());
        assertEquals("Grains",modifiedMaltRecord.getType());
    }

    @Test
    public void deleteMaltRecordTest()
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id", 1));
        MaltRecordEntity existingMaltRecord = (MaltRecordEntity) criteria.uniqueResult();

        maltRecordDao.deleteMaltRecord(existingMaltRecord);

        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertFalse(maltRecordEntityList.contains(existingMaltRecord));
    }
}
