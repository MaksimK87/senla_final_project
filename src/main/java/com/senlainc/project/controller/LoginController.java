package com.senlainc.project.controller;

import com.senlainc.project.entity.Role;
import com.senlainc.project.entity.User;
import com.senlainc.project.service.interf.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginPage(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value="logout", required = false)String logout,Model model){
        model.addAttribute("error",error!=null);
        model.addAttribute("logout", logout!=null);
        return "loginForm";
    }

    /*@PostMapping("/login")
    public String getUser(@ModelAttribute("user") User user, HttpServletRequest request, Model model, HttpSession session) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (password == null || password.isEmpty() || email == null || email.isEmpty()) {
            model.addAttribute("errMessage", "Try to enter correct data!");
            return "loginForm";
        }

        user = userService.signIn(email, password);
        model.addAttribute("user", user);
        session=request.getSession(true);
        logger.debug("--->>> add to session after logination user: "+"ID: "+user.getIdUser()+" Name: "+user.getUserName());
        session.setAttribute("user",user);
        if (user.getRole().equals(Role.ADMIN)) {
            logger.debug("Admin data: " + user.getRole() + ", " + user.getEmail() + ", " + user.getUserName());
            return "adminPage";
        } else {
            session=request.getSession(true);
            session.setAttribute("user",user);
            return "userPage";
        }
    }*/

}
