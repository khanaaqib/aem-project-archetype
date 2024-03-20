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

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/custom/triggerCustomWorkflow"
)
public class RunCustomWorkflow extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        ResourceResolver resourceResolver = request.getResourceResolver();
        WorkflowSession session = resourceResolver.adaptTo(WorkflowSession.class);
        String workflowStatus=" workflow completed";
        try {
            WorkflowModel model = session.getModel("/var/workflow/models/trainingcustomworkflow");
            WorkflowData workflowData = session.newWorkflowData("JCR_PATH",page);
            session.startWorkflow(model,workflowData);
        } catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(workflowStatus);

    }
}
