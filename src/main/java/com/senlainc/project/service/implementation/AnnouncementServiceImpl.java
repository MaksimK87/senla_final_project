package com.senlainc.project.service.implementation;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.dao.interf.AnnouncementDAO;
import com.senlainc.project.entity.Announcement;
import com.senlainc.project.entity.AnnouncementCategory;
import com.senlainc.project.service.interf.AnnouncementService;
import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private static final Logger logger = Logger.getLogger(AnnouncementServiceImpl.class);

    @Autowired
    private AnnouncementDAO announcementDAO;

    @Transactional
    @Override
    public void addOrUpdateAnnouncement(Announcement announcement) {
        announcementDAO.addOrUpdateAnnouncement(announcement);
    }

    @Transactional
    @Override
    public void promoteAnnouncement(Announcement announcement) {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        announcement.setPaymentDate((calendar.getTime()));

        announcementDAO.promoteAnnouncement(announcement);

    }

    @Transactional
    @Override
    public void closeAnnouncement(Announcement announcement) {
        announcementDAO.closeAnnouncement(announcement);
    }

    @Transactional
    @Override
    public List<Announcement> getAllNonTopAnnouncement() throws NoSuchElementServiceException {
        List<Announcement> announcements;
        try {
            announcements = announcementDAO.getAllNonTopAnnouncement();
        } catch (NoSuchElementDAOException e) {
            logger.error("All non top announcements don't available");
            throw new NoSuchElementServiceException(e);
        }
        return announcements;
    }

    @Transactional
    @Override
    public List<Announcement> getAllTopAnnouncement() throws NoSuchElementServiceException {
        List<Announcement> topAnnouncements;
        try {
            topAnnouncements = announcementDAO.getAllTopAnnouncement();
        } catch (NoSuchElementDAOException e) {
            logger.error("All top announcements don't available for this user");
            throw new NoSuchElementServiceException(e);
        }
        return topAnnouncements;
    }

    @Transactional
    @Override
    public Announcement getAnnouncement(long id) throws NoSuchElementServiceException {
        Announcement announcement;
        try {
            announcement = announcementDAO.getAnnouncement(id);
        } catch (NoSuchElementDAOException e) {
            logger.error("Announcement with ID: " + id + " doesn't exist! ");
            throw new NoSuchElementServiceException(e);
        }
        return announcement;
    }

    @Transactional
    @Override
    public List<Announcement> getAnnouncementHistory(long idUser) throws NoSuchElementServiceException {
        List<Announcement> announcementHistory;
        try {
            announcementHistory = announcementDAO.getAnnouncementHistory(idUser);
        } catch (NoSuchElementDAOException e) {
            logger.error("Announcement history for userID: " + idUser + " doesn't exist!");
            throw new NoSuchElementServiceException(e);
        }
        return announcementHistory;
    }

}
