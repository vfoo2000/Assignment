package com.vn.controller;

import com.vn.DAO.MemberDAO;
import com.vn.dto.EditProfileDTO;
import com.vn.service.MemberService;
import com.vn.entities.Member;
import com.vn.util.AccountSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditProfileController {

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    MemberService memberService;

    @Autowired
    AccountSession accountSession;

    @GetMapping("/editEmployee")
    public String editUI(
            ModelMap modelMap,
            HttpSession session
    ){
        accountSession.setCurrentAccount(session,"currentId");

        modelMap.addAttribute("editProfile", new EditProfileDTO());

        return "editEmployee";
    }

    @PostMapping("/editEmployee")
    public String editPostUI(
            @ModelAttribute("editProfile") EditProfileDTO editProfileDTO,
            HttpServletRequest request,
            HttpSession session
    ){
        accountSession.setCurrentAccount(session,"currentId");

        if (memberService.checkBlankEditProfileDTO(editProfileDTO)){
            return "editEmployee";
        } else {
            Integer currentIdUser = (Integer) session.getAttribute("currentId");

            Member updatedMember = memberService.updateFromDTO(editProfileDTO,currentIdUser);
            memberDAO.update(updatedMember);

            request.setAttribute("editMessageSubmit", "Updated successfully!");
            return "editEmployee";
        }
    }
}
