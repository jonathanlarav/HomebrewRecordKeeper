package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class MaltRecordDaoImpl implements MaltRecordDao {

    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        sessionFactory.getCurrentSession().save(maltRecordEntity);
        return maltRecordEntity;
    }

    @Override
    public boolean deleteMaltRecord(MaltRecordEntity maltRecordEntity) {
        try {
            sessionFactory.getCurrentSession().delete(maltRecordEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity) {
        sessionFactory.getCurrentSession().update(maltRecordEntity);
        return maltRecordEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MaltRecordEntity getMaltRecordById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id", id));
        return (MaltRecordEntity) criteria.uniqueResult();
    }

    @Override
    public List<MaltRecordEntity> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from MaltRecordEntity ").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
