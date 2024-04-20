package com.vn.controller;

import com.vn.DAO.MemberDAO;
import com.vn.dto.RegisterDTO;
import com.vn.entities.Member;
import com.vn.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/register")
    public String registerUI(ModelMap modelMap) {
        modelMap.addAttribute("newRegister", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerPostUI(
            @Valid
            @ModelAttribute("newRegister") RegisterDTO RegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        if(bindingResult.hasErrors()){
            return "login";
        }

        Member checkExistMember = memberDAO.getMemberByUserName(RegisterDTO.getUserName());

        if(checkExistMember != null){
            request.setAttribute("messageRegisterFail","The User name already exists! Please try another name!");
            return "register";
        } else {
            Member memberFromDTO = memberService.transferFromDTO(RegisterDTO);
            memberDAO.create(memberFromDTO);
            redirectAttributes.addFlashAttribute("registerSuccess", "Your account has been registered!");
            return "redirect:/login";
        }
    }
}
