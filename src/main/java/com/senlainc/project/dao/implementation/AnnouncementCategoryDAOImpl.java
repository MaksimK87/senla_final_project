package com.senlainc.project.dao.implementation;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.dao.interf.AnnouncementCategoryDAO;
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
public class AnnouncementCategoryDAOImpl implements AnnouncementCategoryDAO {

    private static final Logger logger = Logger.getLogger(AnnouncementCategoryDAOImpl.class);

    private static SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<AnnouncementCategory> getAnnouncementCategory() throws NoSuchElementDAOException {
        Session session = sessionFactory.getCurrentSession();
        List<AnnouncementCategory> announcementCategories=null;
        /*AnnouncementCategory announcementCategory= (AnnouncementCategory) session.createQuery("from AnnouncementCategory where idCategory=:id").setParameter("id",1).uniqueResult();*/
      // logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+announcementCategories);
        Query query = session.createQuery("select a from AnnouncementCategory a",AnnouncementCategory.class);
        announcementCategories = query.getResultList();
        logger.debug("--->>> Get from database announcement categories: "+announcementCategories.toString());

        return Optional.ofNullable(announcementCategories).orElseThrow(NoSuchElementDAOException::new);
    }


}
