package com.vn.DAO.DAOImpl;

import com.vn.DAO.MemberDAO;
import com.vn.entities.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl extends GenericDAOImpl<Member,Integer> implements MemberDAO {

    @Autowired
    SessionFactory sessionFactory;

    public MemberDAOImpl(){
        setEntityType(Member.class);
    }

    @Override
    public Member checkEmailAndPassword(String email, String password) {
        Member member = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            Query query = session.createQuery("SELECT m FROM Member m WHERE m.email like ?1 AND m.password like ?2", Member.class);
            query.setParameter(1, email);
            query.setParameter(2, password);
            member = (Member) query.getSingleResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member getMemberByUserName(String userName) {
        Member member = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            Query query = session.createQuery("SELECT m FROM Member m WHERE m.userName like ?1", Member.class);
            query.setParameter(1, userName);
            member = (Member) query.getSingleResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }


}
