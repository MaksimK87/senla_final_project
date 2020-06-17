package com.senlainc.project.service.interf;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.entity.Announcement;
import com.senlainc.project.entity.AnnouncementCategory;
import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;

import java.util.List;

public interface AnnouncementService {

    void addOrUpdateAnnouncement(Announcement announcement);

    void promoteAnnouncement(Announcement announcement);

    void closeAnnouncement(Announcement announcement);

    List<Announcement> getAllNonTopAnnouncement() throws NoSuchElementServiceException;

    List<Announcement> getAllTopAnnouncement() throws NoSuchElementServiceException;

    Announcement getAnnouncement(long id) throws NoSuchElementServiceException;

    List<Announcement> getAnnouncementHistory(long idUser) throws NoSuchElementServiceException;

}
