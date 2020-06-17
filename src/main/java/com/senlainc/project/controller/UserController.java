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

    @Value("#{regionOptions}")
    private Map<String, String> regionOptions;

    @Value("#{cityOptions}")
    private Map<String, String> cityOptions;

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


    /*@RequestMapping("/showLoginForm")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "loginForm";
    }*/

    @RequestMapping("/showLoginForm")
    public String showLoginForm() {
        return "loginForm";
    }

    @RequestMapping("/processLoginForm")
    public String processLoginForm(@ModelAttribute("user") User user, HttpServletRequest request, Model model,HttpSession session) {
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
    }

    /*@RequestMapping("/processLoginForm")
    public String processLoginForm(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            logger.debug("Incorrect logination data!");
            return "loginForm";
        }
        user = userService.signIn(user.getEmail(), user.getPassword());
        model.addAttribute("user", user);
        if (user.getRole().toString().equals("ADMIN")) {
            logger.debug("Admin data: " + user.getRole() + ", " + user.getEmail() + ", " + user.getUserName());
            return "adminPage";
        } else {
            return "userPage";
        }
    }*/

    @RequestMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("city", cityOptions);
        model.addAttribute("region", regionOptions);
        return "registrationForm";

    }

    @RequestMapping("/processRegistration")
    public String processRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        logger.debug("Registration data from user: " + user.getUserName() + ", " + user.getEmail() +
                ", " + user.getPassword() + ", " + user.getPhoneNumber() + ", " + user.getRegion() + user.getCity());
        user.setRole(Role.USER);
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        if (userService.registration(user)) {

            return "loginForm";
        } else {
            model.addAttribute("errMessage", "Such email already exists! Try another one!");
            return "registrationForm";
        }
    }

    @RequestMapping("/showAdminRegistrationForm")
    public String showAdminRegistrationForm(Model model, HttpSession session) {
        model.addAttribute("admin", new User());
        /*model.addAttribute("city", cityOptions);
        model.addAttribute("region", regionOptions);*/
        session.setAttribute("city", cityOptions);
        session.setAttribute("region", regionOptions);
        return "adminRegistrationForm";
    }

    @RequestMapping("/processAdminRegistration")
    public String processAdminRegistration(@ModelAttribute("admin") @Valid User admin, BindingResult bindingResult, Model model) {
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

    @RequestMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("city", cityOptions);
        model.addAttribute("region", regionOptions);
        return "editUserPage";
    }

    @RequestMapping("/saveProfileChanges")
    public String saveProfileChanges(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("user",user);
        if (user.getRole().equals(Role.ADMIN)) {
            return "adminPage";
        } else {
            return "userPage";
        }
    }

    @RequestMapping("/showAllUsers")
    public String showAllUsers(@ModelAttribute("user") User user, Model model) {
       List<User> users=userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsersPage";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Model model,HttpServletRequest request) {
        long id=Long.valueOf(request.getParameter("userId"));
        logger.debug("delete user with id="+id);
        userService.deleteUser(id);
        List<User> users=userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsersPage";
    }

}