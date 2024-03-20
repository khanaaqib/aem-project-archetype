package com.javadoubts.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface SimpleSiteMapService {
    public List<String> getResult() throws LoginException;

    public ResourceResolver getResourceResolver() throws LoginException;
}
