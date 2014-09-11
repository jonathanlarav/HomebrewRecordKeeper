package com.homebrewrecordkeeper.repositories;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class MaltRecordRepositoryImpl implements MaltRecordRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public MaltRecordRepositoryImpl(SessionFactory sf)
    {
        sessionFactory = sf;
    }
    public MaltRecordRepositoryImpl()
    {

    }

    @Override
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        Integer id = (Integer) sessionFactory.getCurrentSession().save(maltRecordEntity);
        maltRecordEntity.setId(id);
        return maltRecordEntity;
    }

    @Override
    public boolean deleteMaltRecord(int id) {
        try
        {
            sessionFactory.getCurrentSession().delete(getMaltRecordById(id));
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(maltRecordEntity);
        return maltRecordEntity;
    }

    @Override
    public MaltRecordEntity getMaltRecordById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MaltRecordEntity.class);
        criteria.add(Restrictions.eq("Id",id));
        return (MaltRecordEntity)criteria.uniqueResult();
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
