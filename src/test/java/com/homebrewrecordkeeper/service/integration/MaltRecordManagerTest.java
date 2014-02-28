package com.homebrewrecordkeeper.service.integration;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
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
public class MaltRecordManagerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private MaltRecordManager maltRecordManager;

    private SessionFactory sessionFactory;

    @Before
    public void setupTest()
    {
        sessionFactory = maltRecordManager.getMaltRecordDao().getSessionFactory();
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

        MaltRecordEntity returnedMaltRecord = maltRecordManager.updateMaltRecord(existingMaltRecord);

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

        boolean result = maltRecordManager.deleteMaltRecord(existingMaltRecord);

        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertThat(result,equalTo(true));
        assertThat(maltRecordEntityList,not(hasItem(existingMaltRecord)));
    }
    @Test
    public void getAllMaltRecordsTest()
    {
        List<MaltRecordEntity> maltRecordEntityList = maltRecordManager.getAll();
        assertThat(maltRecordEntityList.size(),equalTo(2));
        assertThat(maltRecordEntityList.get(0).getName(),equalTo("Muntons amber malt extract"));
    }
    @Test
    public void getMaltRecordByIdTest()
    {
        MaltRecordEntity maltRecordEntity = maltRecordManager.getMaltRecordById(1);
        assertThat(maltRecordEntity.getId(),equalTo(1));
    }
    @SuppressWarnings("unchecked")
    private MaltRecordEntity createMaltRecord()
    {
        MaltRecordEntity newMaltRecordEntity = new MaltRecordEntity("test1",2,"test1","test1");
        MaltRecordEntity createdMaltRecord = maltRecordManager.addMaltRecord(newMaltRecordEntity);
        List<MaltRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();
        assertThat(createdMaltRecord.getId(),not(equalTo(0)));
        assertThat(maltRecordEntityList,hasItem(newMaltRecordEntity));
        return newMaltRecordEntity;
    }
}
