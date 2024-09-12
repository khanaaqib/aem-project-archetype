package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderComponentChild {
    @Inject
    private String headertitle;

    @Inject
    private String headerlink;

    public String getHeadertitle() {
        return headertitle;
    }

    public String getHeaderlink() {
        return headerlink;
    }
}
