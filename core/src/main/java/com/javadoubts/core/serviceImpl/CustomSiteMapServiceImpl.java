package com.javadoubts.core.serviceImpl;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.javadoubts.core.services.CustomSiteMapService;
import com.javadoubts.core.services.GetServiceUser;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.*;

@Component(service = CustomSiteMapService.class)
public class CustomSiteMapServiceImpl implements CustomSiteMapService {

    private Logger LOG = LoggerFactory.getLogger(CustomSiteMapServiceImpl.class);

    @Reference
    GetServiceUser serviceUser;

    @Reference
    QueryBuilder queryBuilder;

    public Map<String, String> CreateMapQuery(){
        Map<String,String> map = new HashMap<>();
        map.put("type","cq:Page");
        map.put("path", "/content/practice/us/en/aempage");
        return map;
    }

    @Override
    public List<String> getPageResult() throws LoginException, ParserConfigurationException, FileNotFoundException, TransformerException {
        List<String> arrayList = new ArrayList<>();
        ResourceResolver resourceResolver = serviceUser.fetchResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(CreateMapQuery()),session);
        SearchResult searchResult = query.getResult();
        String fileName = "sitemap.xml";
        Iterator<Resource> resources = searchResult.getResources();
        while(resources.hasNext()){
            Resource searchResultResources = resources.next();
            String pagePathUrl = searchResultResources.getPath();
            String searchResourcePath = "http://localhost:4502"+pagePathUrl;
            LocalDate localDate = LocalDate.now();
            arrayList.add(searchResourcePath+"urlPath"+ DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
        }
        writeToXML(arrayList,resourceResolver,fileName);
        return arrayList;
    }

    public void writeToXML( List<String> arrayList,ResourceResolver resourceResolver,String finaName) throws ParserConfigurationException, TransformerException, FileNotFoundException {
           File file = new File(finaName);
           AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
           Document document = documentBuilder.newDocument();
           Element root = document.createElement("urlset");
           root.setAttribute("xmlns","http://www.sitemaps.org/schemas/sitemap/0.9");
           root.setAttribute("xmlns:geo","http://www.google.com/geo/schemas/sitemap/1.0");
           root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
           root.setAttribute("xsi:schemaLocation","http://www.sitemaps.org/schemas/sitemap/0.9" + " " + "http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
           document.appendChild(root);
           for(String path: arrayList){
               Element url = document.createElement("url");
               String [] splitUrlPath = path.split("urlPath");
               Element assetPath = document.createElement("loc");
               Element urlPath = document.createElement("lastmod");
               assetPath.appendChild(document.createTextNode(splitUrlPath[0]));
               urlPath.appendChild(document.createTextNode(splitUrlPath[1]));
               url.appendChild(assetPath);
               url.appendChild(urlPath);
               root.appendChild(url);
           }
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           transformer.setOutputProperty(OutputKeys.INDENT,"yes");
           transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
           transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
           DOMSource domSource = new DOMSource(document);
           StreamResult streamResult = new StreamResult(file);
           transformer.transform(domSource,streamResult);
           InputStream inputStream = new FileInputStream(file);
           assetManager.createAsset("/content/dam/practice/sitemap/" + finaName,inputStream,"application/xml",true);
    }



}
