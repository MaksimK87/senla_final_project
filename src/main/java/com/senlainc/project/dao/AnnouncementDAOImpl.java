package com.senlainc.project.dao;

import com.senlainc.project.entity.Announcement;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementDAOImpl implements AnnouncementDAO {

    private static final Logger logger = Logger.getLogger(AnnouncementDAOImpl.class);

    private static SessionFactory sessionFactory;

    @Override
    public void addOrUpdateAnnouncement(Announcement announcement) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(announcement);
    }

    @Override
    public void promoteAnnouncement(Announcement announcement) {

        Session session=sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Announcement set topStatus=:isTop where idAnnouncement=:id");
        query.setParameter("id",announcement.getIdAnnouncement());
        query.setParameter("isTop",true);
        query.executeUpdate();
    }

    @Override
    public void closeAnnouncement(Announcement announcement) {
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Announcement set activeStatus=:status where idAnnouncement=:id");
        query.setParameter("id",announcement.getIdAnnouncement());
        query.setParameter("status",false);
        query.executeUpdate();
    }

    @Override
    public List<Announcement> getAllNonTopAnnouncement() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement where activeStatus=:activeStatus and topStatus=:topStatus order by creationDate desc" );
        query.setParameter("activeStatus",true);
        query.setParameter("topStatus",false);
        List<Announcement> announcements = query.list();
        return announcements;
    }

    @Override
    public List<Announcement> getAllTopAnnouncement() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement where activeStatus=:activeStatus and topStatus=:topStatus order by paymentDate desc" );
        query.setParameter("activeStatus",true);
        query.setParameter("topStatus",true);
        List<Announcement> announcements = query.list();
        return announcements;
    }

    @Override
    public Announcement getAnnouncement(long id) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement=session.get(Announcement.class,id);
        return announcement;
    }

    @Override
    public List<Announcement> getAnnouncementHistory(long idUser) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement where user=:id order by creationDate desc" );
        query.setParameter("id",idUser);
        List<Announcement> announcements = query.list();
        return announcements;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
