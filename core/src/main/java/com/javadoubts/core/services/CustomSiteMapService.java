package com.javadoubts.core.services;

import org.apache.sling.api.resource.LoginException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.List;

public interface CustomSiteMapService {
    public List<String> getPageResult() throws LoginException, ParserConfigurationException, FileNotFoundException, TransformerException;
}
