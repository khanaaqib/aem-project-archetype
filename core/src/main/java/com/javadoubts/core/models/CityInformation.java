package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class CityInformation {

    @Inject
    private String city;

    @Inject
    private Integer zipcode;

    public Integer getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }
}
