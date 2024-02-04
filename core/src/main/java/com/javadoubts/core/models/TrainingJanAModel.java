package com.javadoubts.core.models;


import com.javadoubts.core.services.TrainingJanAservice;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class TrainingJanAModel {
    @OSGiService
    TrainingJanAservice trainingJanAservice;

    private String url;

    private String clientId;

    private String clientSecret;


    @PostConstruct
    protected void init(){
        url= trainingJanAservice.urlEndpoint();
        clientId = trainingJanAservice.clientid();
        clientSecret = trainingJanAservice.clientSecret();

    }

    public String getUrl() {
        return url;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
