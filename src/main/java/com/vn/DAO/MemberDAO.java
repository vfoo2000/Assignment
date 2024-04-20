package com.vn.DAO;

import com.vn.entities.Member;
import org.hibernate.Session;
import org.hibernate.query.Query;

public interface MemberDAO extends GenericDAO<Member, Integer> {

    public Member checkEmailAndPassword(String account, String password);

    public Member getMemberByUserName(String userName);
}
