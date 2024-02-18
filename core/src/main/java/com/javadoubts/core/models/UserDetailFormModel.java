package com.javadoubts.core.models;

import com.javadoubts.core.services.UserRegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class)
public class UserDetailFormModel {

    @SlingObject
    SlingHttpServletRequest request;

    @OSGiService
    UserRegistrationFormService service;

    private List responseValue;

    @PostConstruct
    protected void init() throws RepositoryException {
        responseValue=service.fetchAllrecords(request);
    }

    public List getResponseValue() {
        return responseValue;
    }
}
