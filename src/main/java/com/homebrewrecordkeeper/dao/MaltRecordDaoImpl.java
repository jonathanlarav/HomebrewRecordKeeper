package com.homebrewrecordkeeper.dao;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaltRecordDaoImpl implements MaltRecordDao {

    @Autowired
    private SessionFactory sessionFactory;

    public MaltRecordDaoImpl(SessionFactory sf) {
        sessionFactory = sf;
    }

    public MaltRecordDaoImpl() {

    }

    @Override
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        sessionFactory.getCurrentSession().save(maltRecordEntity);
        return maltRecordEntity;
    }

    @Override
    public boolean deleteMaltRecord(String id) {
        try {
            sessionFactory.getCurrentSession().delete(getMaltRecordById(id));
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
    public MaltRecordEntity getMaltRecordById(String id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id", id));
        return (MaltRecordEntity) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
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
