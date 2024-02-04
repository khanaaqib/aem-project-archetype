package com.javadoubts.core.servlets;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/workflow/workflowservlet"
)
public class WorkflowExecuteServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
      String workflowStatus =" workflow executed";
        String pagePath = request.getParameter("page");
        ResourceResolver resourceResolver = request.getResourceResolver();

        // first step to fetch the workflow session
        WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);

        // second step to fetch the workflow data
        WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",pagePath);

        // third step to fetch the workflow model
        try {
            WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/training-model");
            workflowSession.startWorkflow(workflowModel,workflowData);
        } catch (WorkflowException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(workflowStatus);

    }
}
