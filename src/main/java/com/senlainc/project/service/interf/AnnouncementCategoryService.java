package com.senlainc.project.service.interf;

import com.senlainc.project.service.serviceexception.NoSuchElementServiceException;

import java.util.Map;

public interface AnnouncementCategoryService {

    public Map<Integer, String> getAnnouncementCategory() throws NoSuchElementServiceException;

}
