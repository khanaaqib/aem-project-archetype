package com.javadoubts.core.servlets;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import sun.tools.serialver.SerialVer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/trigger/CustomServlet"
)
public class TriggerAemWorkflow extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       String payload = request.getParameter("pagePath");
       String status = "workflow executed successfully";
        ResourceResolver resourceResolver = request.getResourceResolver();
        if(payload!=null){
            WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
            try {
                WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/Demo-AEM-Workflow");
                WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",payload);
                workflowSession.startWorkflow(workflowModel,workflowData);
            } catch (WorkflowException e) {
                throw new RuntimeException(e);
            }
        }
        response.getWriter().write(status);
    }
}
