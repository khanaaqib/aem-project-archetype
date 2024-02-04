package com.javadoubts.core.models;

import com.javadoubts.core.services.TrainingJanService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Model(adaptables = Resource.class)
public class TrainingServiceModel {

    @OSGiService
    TrainingJanService config;

    private String user;

    @PostConstruct
    protected void init() throws FileNotFoundException {
        user= config.getuserInfo();
    }

    public String getUser() {
        return user;
    }
}
