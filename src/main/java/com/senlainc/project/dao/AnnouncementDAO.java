package com.senlainc.project.dao;

import com.senlainc.project.entity.Announcement;

import java.util.List;

public interface AnnouncementDAO {

    void addOrUpdateAnnouncement(Announcement announcement);

    void promoteAnnouncement(Announcement announcement);

    void closeAnnouncement(Announcement announcement);

    List<Announcement> getAllNonTopAnnouncement();

    List<Announcement> getAllTopAnnouncement();

    Announcement getAnnouncement(long id);

    List<Announcement> getAnnouncementHistory(long idUser);
}
