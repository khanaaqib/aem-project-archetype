package com.javadoubts.core.services;

import org.apache.sling.api.SlingHttpServletRequest;

import javax.jcr.RepositoryException;
import java.util.List;

public interface UserRegistrationFormService {
    public String addUserDataToNode(SlingHttpServletRequest request) throws RepositoryException;

    public List fetchAllrecords(SlingHttpServletRequest reques) throws RepositoryException;

    public List getSpecificRecord(SlingHttpServletRequest request,String userInfo);
}
