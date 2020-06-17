package com.senlainc.project.dao.implementation;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.dao.interf.AnnouncementDAO;
import com.senlainc.project.entity.Announcement;
import com.senlainc.project.entity.AnnouncementCategory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<Announcement> getAllNonTopAnnouncement() throws NoSuchElementDAOException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.creationDate desc" );//from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.creationDate desc"
        query.setParameter("activeStatus",true);
        query.setParameter("topStatus",false);
        List<Announcement> announcements = query.list();
       // logger.debug("all non top announcements: "+announcements.toString());
        return Optional.ofNullable(announcements).orElseThrow(NoSuchElementDAOException::new);
    }

    @Override
    public List<Announcement> getAllTopAnnouncement() throws NoSuchElementDAOException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.paymentDate desc" );
        query.setParameter("activeStatus",true);
        query.setParameter("topStatus",true);
        List<Announcement> announcements = query.list();
        //logger.debug("all top announcements: "+announcements.toString());
        return Optional.ofNullable(announcements).orElseThrow(NoSuchElementDAOException::new);
    }

    @Override
    public Announcement getAnnouncement(long id) throws NoSuchElementDAOException {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement=session.get(Announcement.class,id);
        return Optional.ofNullable(announcement).orElseThrow(NoSuchElementDAOException::new);
    }

    @Override
    public List<Announcement> getAnnouncementHistory(long idUser) throws NoSuchElementDAOException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from Announcement a where a.user.idUser = :id order by creationDate desc" ); //from Announcement where user=:id order by creationDate desc
        query.setParameter("id",idUser);
        List<Announcement> announcements = query.list();
        
        return Optional.ofNullable(announcements).orElseThrow(NoSuchElementDAOException::new);
    }


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}


