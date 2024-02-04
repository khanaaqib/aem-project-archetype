package com.javadoubts.core.models;

import com.javadoubts.core.services.RegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class)
public class UserProfileModel {

    @SlingObject
    SlingHttpServletRequest request;

    private List<Map<String,String>> response;

    @OSGiService
    RegistrationFormService registrationFormService;

    private String userData;

    @PostConstruct
    protected void init() throws LoginException {
        userData= request.getParameter("userData");
        response = registrationFormService.getUserRecord(userData);
    }

    public List<Map<String, String>> getResponse() {
        return response;
    }
}
