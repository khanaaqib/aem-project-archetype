package com.javadoubts.core.models;

import com.javadoubts.core.services.BasicNewOsgiService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class BasicNewOsgiModel {

    private Logger logger = LoggerFactory.getLogger(BasicNewOsgiModel.class);
    @OSGiService
    BasicNewOsgiService basicNewOsgiService;

    private String urlendPoint;

    private String pass;

    @PostConstruct
    protected void init(){
        logger.info("inside init method");
        urlendPoint = basicNewOsgiService.getUrlEndPoint();
        logger.info("UrlEndpoint Value check:{}",urlendPoint);
        pass= basicNewOsgiService.password();
        logger.info("pass Value check:{}",pass);
    }

    public String getUrlendPoint() {
        return urlendPoint;
    }

    public String getPass() {
        return pass;
    }
}
