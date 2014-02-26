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

    public MaltRecordDaoImpl(SessionFactory sf)
    {
        sessionFactory = sf;
    }
    public MaltRecordDaoImpl()
    {

    }

    @Override
    public int addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return (Integer)sessionFactory.getCurrentSession().save(maltRecordEntity);
    }

    @Override
    public void deleteMaltRecord(String id) {
        sessionFactory.getCurrentSession().delete(getMaltRecordById(id));
    }

    @Override
    public void updateMaltRecord(String id, MaltRecordEntity maltRecordEntity) {
        throw new NotImplementedException();
    }

    @Override
    public MaltRecordEntity getMaltRecordById(String id) {
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
