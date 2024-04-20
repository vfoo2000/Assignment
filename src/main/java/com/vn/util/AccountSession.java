package com.vn.util;

import com.vn.DAO.MemberDAO;
import com.vn.entities.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSession {
    @Autowired
    MemberDAO  memberDAO;

    public void setCurrentAccount(HttpSession session,String stringAttributeUserId) {
        Integer IdUser = (Integer) session.getAttribute(stringAttributeUserId);

        Member member = memberDAO.readById(IdUser);
        session.setAttribute("currentAccount", member);
    }

}
