package com.javadoubts.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

public interface FetchResourceService {
    public ResourceResolver getResourceResolver() throws LoginException;
}
