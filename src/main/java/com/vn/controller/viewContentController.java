package com.vn.controller;


import com.vn.DAO.ContentDAO;
import com.vn.dto.RegisterDTO;
import com.vn.entities.Content;
import com.vn.util.AccountSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class viewContentController {

    @Autowired
    AccountSession accountSession;

    @Autowired
    ContentDAO contentDAO;

    @GetMapping("/viewContent")
    public String addContentUI(
            ModelMap modelMap,
            HttpSession session,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "keyword", defaultValue = "") String keyword
            ) {
        accountSession.setCurrentAccount(session,"currentId");

        int pageSize = 7;
        List<Content> listOfContents = contentDAO.getContentByAuthorIdAndPagingAndSearch(page, pageSize, keyword);

        int numberOfRecord = contentDAO.getCountContentByAuthorIdAndSearch(keyword);
        int numberOfPage = (int) Math.ceil(numberOfRecord * 1.0 / pageSize);

        modelMap.addAttribute("keyword", keyword);
        modelMap.addAttribute("listOfContent", listOfContents);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("noOfPages", numberOfPage);


        return "viewContent";
    }
}
