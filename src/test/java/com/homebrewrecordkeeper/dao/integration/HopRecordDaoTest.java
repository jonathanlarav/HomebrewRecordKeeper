package com.homebrewrecordkeeper.dao.integration;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:homebrewRecordKeeper-servlet.xml")
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

        assertEquals("lbs",returnedHopRecord.getUnit());
        assertEquals("Willamette",returnedHopRecord.getType());
        assertEquals("lbs",modifiedHopRecord.getUnit());
        assertEquals("Willamette",modifiedHopRecord.getType());
    }
    @Test
    public void deleteHopRecordTest()
    {
        HopRecordEntity existingMaltRecord = createHopRecord();

        boolean result = hopRecordDao.deleteHopRecord(existingMaltRecord);

        List<HopRecordEntity> maltRecordEntityList = sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity").list();

        assertTrue(result);
        assertFalse(maltRecordEntityList.contains(existingMaltRecord));
    }
    @Test
    @SuppressWarnings("unchecked")
    public void getAllMaltRecordsTest()
    {
        List<HopRecordEntity> hopRecordEntityList = hopRecordDao.getAll();
        assertEquals(2,hopRecordEntityList.size());
        assertEquals("Perle",hopRecordEntityList.get(0).getType());
    }
    private HopRecordEntity createHopRecord() {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(1.5,"oz",60,"Cascade",5);

        HopRecordEntity createdHopRecord = hopRecordDao.addHopRecord(hopRecordEntity);
        List<HopRecordEntity> hopRecordEntityList = sessionFactory.getCurrentSession().createQuery("from HopRecordEntity").list();

        assertNotNull(createdHopRecord.getId());
        assertTrue(hopRecordEntityList.contains(hopRecordEntity));

        return hopRecordEntity;
    }
}
