package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

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

    @Inject
    private List<VideoDetail> videoDetail;


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

    public List<VideoDetail> getVideoDetail() {
        return videoDetail;
    }
}
