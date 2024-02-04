package com.javadoubts.core.models;

import com.javadoubts.core.services.CustomFormService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

@Model(adaptables = Resource.class)
public class DataModel {

    List<Map<String,String>> message;

    @OSGiService
    CustomFormService customFormService;

    @PostConstruct
    protected void init() throws RepositoryException, LoginException {
        message = customFormService.fetchuserData();
    }

    public List<Map<String,String>> getMessage() {
        return message;
    }
}
