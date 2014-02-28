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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        createMaltRecord();
    }

    @Test
    public void updateMaltRecordTest()
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id", 1));
        MaltRecordEntity existingMaltRecord = (MaltRecordEntity) criteria.uniqueResult();

        existingMaltRecord.setName("New Malt");
        existingMaltRecord.setType("Grains");

        MaltRecordEntity returnedMaltRecord = maltRecordDao.updateMaltRecord(existingMaltRecord);

        MaltRecordEntity modifiedMaltRecord = (MaltRecordEntity) criteria.uniqueResult();

        assertThat(returnedMaltRecord.getName(),equalTo("New Malt"));
        assertThat(returnedMaltRecord.getType(),equalTo("Grains"));
        assertThat(modifiedMaltRecord.getName(),equalTo("New Malt"));
        assertThat(modifiedMaltRecord.getType(),equalTo("Grains"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteMaltRecordTest()
    {
        MaltRecordEntity existingMaltRecord = createMaltRecord();

        boolean result = maltRecordDao.deleteMaltRecord(existingMaltRecord);

        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertThat(result,equalTo(true));
        assertThat(maltRecordEntityList,not(hasItem(existingMaltRecord)));
    }
    @Test
    @SuppressWarnings("unchecked")
    public void getAllMaltRecordsTest()
    {
        List<MaltRecordEntity> maltRecordEntityList = maltRecordDao.getAll();
        assertThat(maltRecordEntityList,hasSize(2));
        assertThat(maltRecordEntityList.get(0).getName(),equalTo("Muntons amber malt extract"));
    }
    @Test
    public void getMaltRecordByIdTest()
    {
        MaltRecordEntity maltRecordEntity = maltRecordDao.getMaltRecordById(1);
        assertThat(maltRecordEntity.getId(),equalTo(1));
    }
    @SuppressWarnings("unchecked")
    private MaltRecordEntity createMaltRecord()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        MaltRecordEntity createdMaltRecord = maltRecordDao.addMaltRecord(newMaltRecordEntity);
        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertThat(createdMaltRecord.getId(),not(equalTo(0)));
        assertThat(maltRecordEntityList,hasItem(newMaltRecordEntity));
        return newMaltRecordEntity;
    }
}
