package com.javadoubts.core.models;

import com.javadoubts.core.services.RegistrationFormService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Model(adaptables = Resource.class)
public class RegisteredUserDataModel {

    @OSGiService
    RegistrationFormService registrationFormService;

    private List<Map<String,String>> message;

    @PostConstruct
    protected void init() throws LoginException {
        message=registrationFormService.getFormData();
    }

    public List<Map<String, String>> getMessage() {
        return message;
    }
}
