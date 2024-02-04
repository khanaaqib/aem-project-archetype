package com.javadoubts.core.models;

import com.javadoubts.core.services.DemoService;
import com.javadoubts.core.services.TestService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class TestConfigModel {
    private String fname;
    private String lname;

    private String[] email;

    @OSGiService
    TestService testConfig;

    @PostConstruct
    protected void init(){
        fname = testConfig.getFirstName();
        lname= testConfig.getLastName();
        email= testConfig.getEmail();
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String[] getEmail() {
        return email;
    }
}
