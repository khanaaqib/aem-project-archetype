package com.javadoubts.core.models;

import com.javadoubts.core.services.FirstConfigService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class FIrstConfigModel {
    @OSGiService
    FirstConfigService firstConfigService;

    private String fname;
    private String lanme;

    private String clientId;

    @PostConstruct
    protected  void init(){
        fname= firstConfigService.getFirstName();
        lanme = firstConfigService.getLastName();
        clientId = firstConfigService.getClientId();

    }

    public String getFname() {
        return fname;
    }

    public String getLanme() {
        return lanme;
    }

    public String getClientId() {
        return clientId;
    }
}
