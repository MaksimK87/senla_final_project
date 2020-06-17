package com.senlainc.project.dao.interf;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.entity.Announcement;
import com.senlainc.project.entity.AnnouncementCategory;

import java.util.List;

public interface AnnouncementDAO {

    void addOrUpdateAnnouncement(Announcement announcement);

    void promoteAnnouncement(Announcement announcement);

    void closeAnnouncement(Announcement announcement);

    List<Announcement> getAllNonTopAnnouncement() throws NoSuchElementDAOException;

    List<Announcement> getAllTopAnnouncement() throws NoSuchElementDAOException;

    Announcement getAnnouncement(long id) throws NoSuchElementDAOException;

    List<Announcement> getAnnouncementHistory(long idUser) throws NoSuchElementDAOException;


}
