package com.senlainc.project.service.interf;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.entity.AnnouncementCategory;
import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;

import java.util.List;

public interface AnnouncementCategoryService {

    public List<AnnouncementCategory> getAnnouncementCategory() throws NoSuchElementServiceException;

}
