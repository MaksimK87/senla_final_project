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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    /*@Value("#{regionOptions}")
    private Map<String, String> regionOptions;

    @Value("#{cityOptions}")
    private Map<String, String> cityOptions;*/

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        /*model.addAttribute("city", cityOptions);
        model.addAttribute("region", regionOptions);*/
        return "registrationForm";

    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        logger.debug("Registration data from user: " + user.getUserName() + ", " + user.getEmail() +
                ", " + user.getPassword() + ", " + user.getPhoneNumber() + ", " + user.getRegion() + user.getCity());
        user.setRole(Role.USER);


        if (userService.registration(user)) {
            logger.debug("User was registered successfully!");
            return "loginForm";
        } else {
            model.addAttribute("errMessage", "Such email already exists! Try another one!");
            return "registrationForm";
        }
    }
}
