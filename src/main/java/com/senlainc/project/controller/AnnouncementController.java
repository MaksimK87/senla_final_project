package com.senlainc.project.controller;

import com.senlainc.project.entity.Announcement;
import com.senlainc.project.entity.AnnouncementCategory;
import com.senlainc.project.entity.User;
import com.senlainc.project.service.interf.AnnouncementCategoryService;
import com.senlainc.project.service.interf.AnnouncementService;
import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementCategoryService announcementCategoryService;

    private static final Logger logger = Logger.getLogger(AnnouncementController.class);

    @ModelAttribute("categories")
    public List<AnnouncementCategory> getCategories() {
        List<AnnouncementCategory> announcementCategories=null;
        try {
            announcementCategories = announcementCategoryService.getAnnouncementCategory();
        } catch (NoSuchElementServiceException e) {
            logger.error("--->>> Exception while getting categories! ");
            e.printStackTrace();
        }
        return announcementCategories;
    }


    @RequestMapping("/showAllAnnouncemens")
    public String showMainPage(Model model) {
        List<Announcement> nonTopAnnouncements;
        List<Announcement> topAnnouncements;
        try {
            //DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            nonTopAnnouncements = announcementService.getAllNonTopAnnouncement();
            topAnnouncements = announcementService.getAllTopAnnouncement();
            model.addAttribute("top", topAnnouncements);
            model.addAttribute("nonTop", nonTopAnnouncements);
        } catch (NoSuchElementServiceException e) {
            e.printStackTrace();
        }
        return "mainPage";
    }

    @RequestMapping("/openDetails")
    public String showDetails(Model model) {

        return "mainPage";
    }

    @RequestMapping("/showNewAnnouncementForm")
    public String showNewAnnouncementForm(HttpSession session, Model model, HttpServletRequest request) {
        session=request.getSession(false);
        if(session==null){
            return "loginPage";
        }
        User user= (User) session.getAttribute("user");
        logger.debug("New announcement for user: " + user);
        Announcement announcement = new Announcement();
        /*try {
            List<AnnouncementCategory> announcementCategories = announcementCategoryService.getAnnouncementCategory();
            model.addAttribute("categories", announcementCategories);
        } catch (NoSuchElementServiceException e) {
            e.printStackTrace();
        }*/
        announcement.setUser(user);
        model.addAttribute("announcement", announcement);
        return "announcementForm";
    }

    @RequestMapping("/processNewAnnouncement")
    public String processNewAnnouncement(@ModelAttribute("announcement") @Valid Announcement announcement,
                                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "announcementForm";
        }
        announcementService.addOrUpdateAnnouncement(announcement);
        model.addAttribute("successMessage", "New announcement has been added!");
        return "userPage";
    }
}
