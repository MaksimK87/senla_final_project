package com.senlainc.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
        boolean isAuth=false;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            isAuth = authentication.isAuthenticated();
            Object details=authentication.getDetails();
        }
        model.addAttribute("isAuth",isAuth);



        return "mainPage";
    }


}
