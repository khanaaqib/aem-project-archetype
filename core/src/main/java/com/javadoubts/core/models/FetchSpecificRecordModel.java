package com.javadoubts.core.models;

import com.javadoubts.core.services.UserRegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class)
public class FetchSpecificRecordModel {
    @SlingObject
    SlingHttpServletRequest request;

    List responseCheck;

    @OSGiService
    UserRegistrationFormService userRegistrationFormService;

    private String userProfile;

    @PostConstruct
    protected void init(){
        userProfile = request.getParameter("userProfile");
        responseCheck = userRegistrationFormService.getSpecificRecord(request,userProfile);

    }

    public List getResponseCheck() {
        return responseCheck;
    }
}
