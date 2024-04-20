package com.vn.DAO.DAOImpl;

import com.vn.DAO.GenericDAO;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class GenericDAOImpl<ET,Type> implements GenericDAO<ET,Type> {

    @Autowired
    SessionFactory sessionFactory;

    @Setter
    private Class<?> entityType;

    @Override
    public ET create(ET entity) {
        //TCL transaction control language
        try (Session session = sessionFactory.getCurrentSession()){
            session.getTransaction().begin();

            session.save(entity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<ET> readAll() {
        List<ET> entites = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            //PLQL
            Query query = session.createQuery("SELECT e FROM " + entityType.getName() + " e", entityType);
            entites = query.getResultList();

            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entites;
    }

    @Override
    public ET readById(Type id) {
        Object obj = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            //createQuery => hql. jpql
            Query query = session.createQuery("SELECT e FROM " + entityType.getName() + " e WHERE e.id = ?1", entityType);
            query.setParameter(1, id);
            obj = query.getSingleResult();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ET) obj;
    }

    @Override
    public ET update(ET entity) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            // save or update
            session.update(entity);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public ET deleteById(Type id) {
        ET entity = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            //PLQL
            entity = (ET) session.find(entityType, id);
            session.delete(entity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
