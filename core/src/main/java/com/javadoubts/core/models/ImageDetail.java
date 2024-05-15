package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class ImageDetail {

    @Inject
    private String image;

    @Inject
    private String imageTitle;

    @Inject
    private String imageDesc;


    @Inject
    private String imagecta;

    public String getImage() {
        return image;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public String getImagecta() {
        return imagecta;
    }
}
