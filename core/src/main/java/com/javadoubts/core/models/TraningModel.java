package com.javadoubts.core.models;

import com.javadoubts.core.services.TrainingService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class TraningModel {

    @OSGiService
    TrainingService training;

    private String fname;

    private  String lname;

    private  String[] phone;


    @PostConstruct
    protected void init(){
        fname= training.getFirstName();
        lname=training.getLastName();
        phone = training.getPhoneNumber();
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String[] getPhone() {
        return phone;
    }
}
