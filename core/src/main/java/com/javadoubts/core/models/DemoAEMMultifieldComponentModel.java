package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class DemoAEMMultifieldComponentModel {

    @Inject
    private String title;


    @Inject
    private List<ImageDetail> imageDetail;

    public String getTitle() {
        return title;
    }

    public List<ImageDetail> getImageDetail() {
        return imageDetail;
    }
}
