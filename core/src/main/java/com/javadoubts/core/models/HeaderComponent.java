package com.javadoubts.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderComponent {

    @Inject
    private String logoimg;

    @Inject
    private List<HeaderComponentChild> navigation;

    public String getLogoimg() {
        return logoimg;
    }

    public List<HeaderComponentChild> getNavigation() {
        return navigation;
    }
}
