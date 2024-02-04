package com.javadoubts.core.servlets;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.javadoubts.core.services.RegistrationFormService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/form/registrationForm"
)
public class RegistrationFormServlet extends SlingAllMethodsServlet {
    private Logger logger = LoggerFactory.getLogger(RegistrationFormServlet.class);
    @Reference
    RegistrationFormService registrationFormService;
    @Reference
    private MessageGatewayService messageGatewayService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String formDataResponse = null;
        JSONObject jsonObject = new JSONObject();
        boolean sent= false;
        PrintWriter output = response.getWriter();
        try {
            formDataResponse = registrationFormService.addUserData(request);
            if(formDataResponse.equals("user is already exist")){
                logger.info("user is already exist");
                jsonObject.put("message",sent);
               // response.getWriter().write("failure");
            } else{
                logger.info("send email fucntion");
                String [] recipients = {"testdevdev0991@gmail.com"};
                Email email = new HtmlEmail();
                for(String recipient: recipients){
                    email.addTo(recipient,recipient);
                }
                email.setSubject("This email is for testing purpose");
                email.setMsg("this is email body");
                MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
                if(messageGateway!=null){
                    messageGateway.send(email);
                }else{
                    logger.info("messageGateway is failing");
                }
                response.setStatus(200);
                sent=true;
                jsonObject.put("success",sent);

                output.flush();

            }
        } catch (RepositoryException e) {
            logger.info("error in RegistrationFormServlet");
            response.getWriter().write(formDataResponse.toString());
        } catch (EmailException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        output.write(jsonObject.toString());

    }
}
