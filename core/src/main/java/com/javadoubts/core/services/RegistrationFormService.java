package com.javadoubts.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface RegistrationFormService {
    public String addUserData(SlingHttpServletRequest request) throws RepositoryException;

    public List<Map<String,String>> getFormData() throws LoginException;


    public List<Map<String,String>> getUserRecord(String userData) throws LoginException;


}
