package com.javadoubts.core.models;


import com.javadoubts.core.services.RevisedService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class RevisedModel {
     private String fileInfoValue;

    private String clientid;

    private String clientSecret;

    @OSGiService
    RevisedService revisedService;

    @PostConstruct
    protected void init(){
        fileInfoValue= revisedService.getReadFile();
        clientid = revisedService.getClientId();
        clientSecret=revisedService.getClientSecret();
    }

    public String getFileInfoValue() {
        return fileInfoValue;
    }

    public String getClientid() {
        return clientid;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
