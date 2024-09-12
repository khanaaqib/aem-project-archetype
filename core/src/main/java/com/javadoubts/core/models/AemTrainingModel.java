package com.javadoubts.core.models;

import com.javadoubts.core.services.AemTrainingService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class AemTrainingModel {

    private String url;

    private String clientId;

    @OSGiService
    AemTrainingService aemTrainingService;

    @PostConstruct
    protected void init(){
        clientId=aemTrainingService.getClient();
        url= aemTrainingService.getURl();
    }

    public String getUrl() {
        return url;
    }

    public String getClientId() {
        return clientId;
    }


}
