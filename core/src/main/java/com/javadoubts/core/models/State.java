package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
public class State {
    @Inject
    private String state;

    @Inject
    private String cm;

    @Inject
    private String imagePath;

    @Inject
    private String imageAltText;

    @Inject
    private List<City> cityInformation;

    public String getState() {
        return state;
    }

    public String getCm() {
        return cm;
    }

    public List<City> getCityInformation() {
        return cityInformation;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageAltText() {
        return imageAltText;
    }
}
