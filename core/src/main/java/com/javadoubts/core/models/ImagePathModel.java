package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class ImagePathModel {

    @Inject
    private String imageTitle;

    @Inject
    private String imagedesc;

    @Inject
    private String imagePath;

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImagedesc() {
        return imagedesc;
    }

    public String getImagePath() {
        return imagePath;
    }
}
