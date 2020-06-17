package com.senlainc.project.dao.interf;

import com.senlainc.project.dao.daoexception.NoSuchElementDAOException;
import com.senlainc.project.entity.AnnouncementCategory;

import java.util.List;

public interface AnnouncementCategoryDAO {

    public List<AnnouncementCategory> getAnnouncementCategory() throws NoSuchElementDAOException;

}
