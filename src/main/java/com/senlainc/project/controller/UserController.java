package com.senlainc.project.controller;

import com.senlainc.project.entity.Role;
import com.senlainc.project.entity.User;
import com.senlainc.project.service.interf.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    /*@Value("#{regionOptions}")
    private Map<String, String> regionOptions;

    @Value("#{cityOptions}")
    private Map<String, String> cityOptions;*/

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    private User setUpUserForm() {
        return new User();
    }


    @RequestMapping("/showMainPage")
    public String showMainPage() {
        return "mainPage";
    }



    @RequestMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        /*model.addAttribute("city", cityOptions);
        model.addAttribute("region", regionOptions);*/
        return "editUserPage";
    }

    @RequestMapping("/saveProfile")
    public String saveProfile(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("user",user);
        if (user.getRole().equals(Role.ADMIN)) {
            return "adminPage";
        } else {
            return "userPage";
        }
    }
}