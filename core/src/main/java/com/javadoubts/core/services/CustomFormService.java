package com.javadoubts.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface CustomFormService {
    public String getUserData(SlingHttpServletRequest request) throws LoginException, RepositoryException;

    public List<Map<String,String>> fetchuserData() throws RepositoryException, LoginException;
}
