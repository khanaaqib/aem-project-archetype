package com.javadoubts.core.models;

import com.javadoubts.core.services.DemoService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class DemoTestService {
    private String message;

    @OSGiService
    DemoService demoService;

    @PostConstruct
    public void init(){
        message = demoService.getFileInfo();
    }

    public String getMessage() {
        return message;
    }
}
