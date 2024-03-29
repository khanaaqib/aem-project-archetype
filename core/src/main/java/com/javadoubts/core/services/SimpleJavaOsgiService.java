package com.javadoubts.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface SimpleJavaOsgiService {

    public ResourceResolver getResourceResolver() throws LoginException;
    public ArrayList<String> getPageResult() throws LoginException, FileNotFoundException, ParserConfigurationException, TransformerException;
}
