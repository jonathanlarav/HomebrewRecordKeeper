package com.homebrewrecordkeeper.dao.integration;

import com.homebrewrecordkeeper.config.ApplicationConfig;
import com.homebrewrecordkeeper.dao.HopRecordDao;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
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
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration(transactionManager="transactionManager")
public class HopRecordDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private HopRecordDao hopRecordDao;

    private SessionFactory sessionFactory;

    @Before
    public void setupTest()
    {
        sessionFactory = hopRecordDao.getSessionFactory();
    }

    @Test
    public void addHopRecordTest()
    {
        createHopRecord();
    }
    @Test
    public void updateHopRecordTest()
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HopRecordEntity.class);
        criteria.add(Restrictions.eq("Id", 1));
        HopRecordEntity existingHopRecordEntity = (HopRecordEntity) criteria.uniqueResult();

        existingHopRecordEntity.setUnit("lbs");
        existingHopRecordEntity.setType("Willamette");
        HopRecordEntity returnedHopRecord = hopRecordDao.updateHopRecord(existingHopRecordEntity);
        HopRecordEntity modifiedHopRecord = (HopRecordEntity) criteria.uniqueResult();

        assertThat(returnedHopRecord.getUnit(),equalTo("lbs"));
        assertThat(returnedHopRecord.getType(),equalTo("Willamette"));
        assertThat(modifiedHopRecord.getUnit(),equalTo("lbs"));
        assertThat(modifiedHopRecord.getType(),equalTo("Willamette"));
    }
    @Test
    public void deleteHopRecordTest()
    {
        HopRecordEntity existingMaltRecord = createHopRecord();

        boolean result = hopRecordDao.deleteHopRecord(existingMaltRecord);

        List<HopRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertThat(result,is(true));
        assertThat(maltRecordEntityList,not(hasItem(existingMaltRecord)));
    }
    @Test
    @SuppressWarnings("unchecked")
    public void getAllMaltRecordsTest()
    {
        List<HopRecordEntity> hopRecordEntityList = hopRecordDao.getAll();
        assertThat(hopRecordEntityList,hasSize(2));
        assertThat(hopRecordEntityList.get(0).getType(),equalTo("Perle"));
    }
    @Test
    public void getMaltRecordByIdTest()
    {
        HopRecordEntity hopRecordEntity = hopRecordDao.getHopRecordById(1);
        assertThat(hopRecordEntity.getId(),equalTo(1));
    }
    @SuppressWarnings("unchecked")
    private HopRecordEntity createHopRecord() {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(1.5,"oz",60,"Cascade",5);

        HopRecordEntity createdHopRecord = hopRecordDao.addHopRecord(hopRecordEntity);
        List<HopRecordEntity> hopRecordEntityList = sessionFactory.getCurrentSession().createQuery("from HopRecordEntity").list();

        assertThat(createdHopRecord.getId(),not(equalTo(0)));
        assertThat(hopRecordEntityList,hasItem(hopRecordEntity));

        return hopRecordEntity;
    }
}
