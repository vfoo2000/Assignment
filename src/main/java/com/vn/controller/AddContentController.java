package com.vn.controller;

import com.vn.DAO.ContentDAO;
import com.vn.DAO.MemberDAO;
import com.vn.dto.AddContentDTO;
import com.vn.entities.Content;
import com.vn.entities.Member;
import com.vn.service.ContentService;
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
public class AddContentController {

    @Autowired
    ContentDAO contentDAO;

    @Autowired
    ContentService contentService;

    @Autowired
    AccountSession accountSession;

    @GetMapping("/addContent")
    public String addContentUI(
            ModelMap modelMap,
            HttpSession session) {
        modelMap.addAttribute("newContent", new AddContentDTO());

        accountSession.setCurrentAccount(session,"currentId");

        return "addContent";
    }

    @PostMapping("/addContent")
    public String addContent(
            @ModelAttribute("newContent") AddContentDTO addContentDTO,
            HttpSession session,
            HttpServletRequest request)
    {
        accountSession.setCurrentAccount(session,"currentId");

        if (contentService.checkBlankAddContenteDTO(addContentDTO)){
            return "addContent";
        }

        Content existContent = contentDAO.getContentTitle(addContentDTO.getTitle());

        Integer currentIdUser = (Integer) session.getAttribute("currentId");

        if (existContent==null) {
            Content newContent = contentService.transferFromDTO(addContentDTO,currentIdUser);
            contentDAO.create(newContent);
            request.setAttribute("addMessageSubmit", "Added successfully!");
            return "addContent";
        } else {
            Content newContent = contentService.updateFromDTO(addContentDTO,currentIdUser);
            contentDAO.update(newContent);
            request.setAttribute("addMessageSubmit", "Updated successfully!");
            return "addContent";
        }
    }
}
