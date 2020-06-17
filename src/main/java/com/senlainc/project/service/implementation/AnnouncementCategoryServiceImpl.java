package com.senlainc.project.service.implementation;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.dao.interf.AnnouncementCategoryDAO;
import com.senlainc.project.dao.interf.AnnouncementDAO;
import com.senlainc.project.entity.AnnouncementCategory;
import com.senlainc.project.service.interf.AnnouncementCategoryService;
import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementCategoryServiceImpl implements AnnouncementCategoryService {

    private static final Logger logger = Logger.getLogger(AnnouncementCategoryServiceImpl.class);

    @Autowired
    private AnnouncementCategoryDAO announcementCategoryDAO;

    @Transactional
    @Override
    public List<AnnouncementCategory> getAnnouncementCategory() throws NoSuchElementServiceException {
        List<AnnouncementCategory> announcementCategories;
        try {
            announcementCategories = announcementCategoryDAO.getAnnouncementCategory();
        } catch (NoSuchElementDAOException e) {
            logger.error("Announcement category doesn't available!");
            throw new NoSuchElementServiceException(e);
        }
        return announcementCategories;
    }
}
