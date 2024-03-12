package com.javadoubts.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class ImageFieldsModel {
    @Inject
    private String imagePath;

    @Inject
    private String imgtitle;

    @Inject
    private String imgdesc;

    public String getImagePath() {
        return imagePath;
    }

    public String getImgtitle() {
        return imgtitle;
    }

    public String getImgdesc() {
        return imgdesc;
    }
}
