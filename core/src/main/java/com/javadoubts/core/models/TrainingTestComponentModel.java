package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TrainingTestComponentModel {
    @Inject
    private String title;

    @Inject
    private String subtitle;

    @Inject
    private List<ImageModel> imageCarousel;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<ImageModel> getImageCarousel() {
        return imageCarousel;
    }
}
