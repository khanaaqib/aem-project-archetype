package com.javadoubts.core.serviceImpl;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.javadoubts.core.services.SimpleJavaOsgiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.jcr.Session;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = SimpleJavaOsgiService.class)
public class SimpleJavaOsgiServiceImpl implements SimpleJavaOsgiService {

    @Reference
    QueryBuilder queryBuilder;

   @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public ResourceResolver getResourceResolver() throws LoginException {
        ResourceResolver resourceResolver=null;
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"sitemapserviceuser");
        resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
        return resourceResolver;
    }

    public Map<String,String> CreateSearchQuery(){
        Map<String,String>map= new HashMap<>();
        map.put("type","cq:Page");
        map.put("path","/content/practice/us/en/demo");
        return map;
    }

    @Override
    public ArrayList<String> getPageResult() throws LoginException, FileNotFoundException, ParserConfigurationException, TransformerException {
        ArrayList<String> list = new ArrayList<>();
        ResourceResolver resourceResolver = getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(CreateSearchQuery()), session);
        SearchResult result = query.getResult();
        Iterator<Resource> itr = result.getResources();
        String fileName= "sitemap.xml";
        while (itr.hasNext()) {
            Resource resource = itr.next();
            String pagePath = "http://localhost:4502" + resource.getPath();
            LocalDate date = LocalDate.now();
            list.add(pagePath + "urlPath" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date));
        }
        WriteToXML(list,resourceResolver,fileName);
        return list;
    }

    public void WriteToXML(ArrayList<String> arrayList,ResourceResolver resourceResolver, String fileName) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        File file = new File(fileName);
        AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
        DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root= document.createElement("urlset");
        root.setAttribute("xmlns","http://www.sitemaps.org/schemas/sitemap/0.9");
        root.setAttribute("xmlns:geo","http://www.google.com/geo/schemas/sitemap/1.0");
        root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation","http://www.sitemaps.org/schemas/sitemap/0.9"+" "+ "http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
        document.appendChild(root);
        for(String path : arrayList){
            Element url = document.createElement("url");
            Element urlPath = document.createElement("loc");
            Element date = document.createElement("lastmod");
            String [] urlPathSplit = path.split("urlPath");
            urlPath.appendChild(document.createTextNode(urlPathSplit[0]));
            date.appendChild(document.createTextNode(urlPathSplit[1]));
            url.appendChild(urlPath);
            url.appendChild(date);
            root.appendChild(url);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}/indent-amount","4");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(fileName);
        transformer.transform(domSource,streamResult);
        InputStream inputStream = new FileInputStream(file);
        assetManager.createAsset("/content/dam/practice/xmlfile/" + fileName, inputStream,"aplication/xml",true);




    }
}
