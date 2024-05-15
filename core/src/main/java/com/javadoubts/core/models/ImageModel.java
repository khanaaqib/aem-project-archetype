package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageModel {

    @Inject
    private String image;

    @Inject
    private String imageTitle;

    @Inject
    private String imageSubTitle;

    @Inject
    private String ctabutton;

    @Inject
    private List<ImageSubModel> imageSubCarousel;

    public String getImage() {
        return image;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageSubTitle() {
        return imageSubTitle;
    }

    public String getCtabutton() {
        return ctabutton;
    }

    public List<ImageSubModel> getImageSubCarousel() {
        return imageSubCarousel;
    }
}
