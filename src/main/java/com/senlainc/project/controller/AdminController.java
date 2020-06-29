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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

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

    @GetMapping("/adminRegistration")
    public String adminRegistration(Model model, HttpSession session) {
        model.addAttribute("admin", new User());
        /*session.setAttribute("city", cityOptions);
        session.setAttribute("region", regionOptions);*/
        return "adminRegistrationForm";
    }

    @PostMapping("/adminRegistration")
    public String addAdmin(@ModelAttribute("admin") @Valid User admin, BindingResult bindingResult, Model model) {

        logger.debug("Registration data from admin: " + admin.getUserName() + ", " + admin.getEmail() +
                ", " + admin.getPassword() + ", " + admin.getPhoneNumber() + ", " + admin.getRegion() + admin.getCity());

        admin.setRole(Role.ADMIN);

        if (bindingResult.hasErrors()) {
            logger.debug("Registration data has errors!");
            return "adminRegistrationForm";
        }

        if (userService.registration(admin)) {
            model.addAttribute("successMessage", "Registration has completed successfully!");
            return "adminPage";
        } else {
            model.addAttribute("errmessage", "Such email already exists! Try another one!");
            return "adminRegistrationForm";
        }
    }

    @RequestMapping("/showAllUsers")
    public String showAllUsers(@ModelAttribute("user") User user, Model model) {

        List<User> users=userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsersPage";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Model model, HttpServletRequest request) {

        long id=Long.valueOf(request.getParameter("userId"));

        logger.debug("delete user with id="+id);

        userService.deleteUser(id);

        List<User> users=userService.getAllUsers();

        model.addAttribute("users", users);

        return "allUsersPage";
    }

}
