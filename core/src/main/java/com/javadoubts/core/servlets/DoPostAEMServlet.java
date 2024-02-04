package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/post/aemservlet"
)
public class DoPostAEMServlet extends SlingAllMethodsServlet {

    private Logger logVlaue = LoggerFactory.getLogger(DoPostAEMServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNo = request.getParameter("contact");
        String email = request.getParameter("email");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName",firstName);
            jsonObject.put("lastName",lastName);
            jsonObject.put("contactInfo",contactNo);
            jsonObject.put("EmailAddress",email);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        logVlaue.info("form value check:{},{},{},{}",firstName,lastName,contactNo,email);
        logVlaue.info("JSON Object check:::{}",jsonObject);
        response.getWriter().write("value Added Succussfully");


    }
}
