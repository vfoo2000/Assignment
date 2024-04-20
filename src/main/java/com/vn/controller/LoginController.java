package com.vn.controller;

import com.vn.DAO.MemberDAO;
import com.vn.dto.LoginDTO;
import com.vn.entities.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;


@Controller
public class LoginController {

    @Autowired
    MemberDAO memberDAO;

    @GetMapping(value = {"/login","/",""})
    public String loginUI(ModelMap modelMap){
        modelMap.addAttribute("loginMember",new LoginDTO());
        return "login";
    }

    @PostMapping("login")
    public String loginUIPost(
        @Valid
        @ModelAttribute("loginMember") LoginDTO loginDTO,
        BindingResult bindingResult,
        HttpSession session,
        HttpServletRequest request
    ){
        if(bindingResult.hasErrors()){
            return "login";
        }

        Member member = memberDAO.checkEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if (member == null){
            request.setAttribute("messageLoginFail","Your email and password is wrong!");
            return "login";
        } else {
            session.setAttribute("currentId",member.getId());
            return "redirect:/editEmployee";
        }
    }
}
