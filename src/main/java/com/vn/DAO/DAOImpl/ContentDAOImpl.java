package com.vn.DAO.DAOImpl;

import com.vn.DAO.ContentDAO;
import com.vn.entities.Content;
import com.vn.entities.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ContentDAOImpl extends GenericDAOImpl<Content,Integer> implements ContentDAO {

    public ContentDAOImpl(){
        setEntityType(Content.class);
    }

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Content getContentTitle(String title) {
        Content content = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            Query query = session.createQuery("SELECT c FROM Content c WHERE c.title like ?1", Content.class);
            query.setParameter(1, title);
            content = (Content) query.getSingleResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public List<Content> getContentByAuthorIdAndPagingAndSearch(Integer pageNum, Integer pageSize, String search) {
        List<Content> contents = null;
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createQuery(
                    "SELECT c FROM Content c WHERE c.title like ?1 OR c.brief like ?2 OR c.content like ?3", Content.class);

            query.setParameter(1, "%" + search + "%");
            query.setParameter(2, "%" + search + "%");
            query.setParameter(3, "%" + search + "%");
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);

            contents = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contents;
    }

    @Override
    public int getCountContentByAuthorIdAndSearch(String search) {
        int count = 0;
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createQuery(
                    "SELECT c FROM Content c WHERE c.title like ?1 OR c.brief like ?2 OR c.content like ?3", Content.class);

            query.setParameter(1, "%" + search + "%");
            query.setParameter(2, "%" + search + "%");
            query.setParameter(3, "%" + search + "%");

            count = query.getResultList().size();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
