package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.HopRecordEntity;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class HopRecordDaoImpl implements HopRecordDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HopRecordEntity addHopRecord(HopRecordEntity hopRecordEntity) {
        sessionFactory.getCurrentSession().save(hopRecordEntity);
        return hopRecordEntity;
    }

    @Override
    public HopRecordEntity updateHopRecord(HopRecordEntity hopRecordEntity) {
        sessionFactory.getCurrentSession().update(hopRecordEntity);
        return hopRecordEntity;
    }

    @Override
    public boolean deleteHopRecord(HopRecordEntity hopRecordEntity) {
        try {
            sessionFactory.getCurrentSession().delete(hopRecordEntity);
            return true;
        }catch (Exception c)
        {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HopRecordEntity> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from HopRecordEntity").list();
    }

    @Override
    public HopRecordEntity getHopRecordById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HopRecordEntity.class);
        criteria.add(Restrictions.eq("Id", id));
        return (HopRecordEntity) criteria.uniqueResult();
    }
}
