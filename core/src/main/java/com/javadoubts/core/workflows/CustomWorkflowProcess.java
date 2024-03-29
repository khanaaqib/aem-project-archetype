package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.javadoubts.core.servlets.DemoPathPostServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = WorkflowProcess.class,
          property = {"process.label=Custom workflow Process"}
)
public class CustomWorkflowProcess implements WorkflowProcess {
    private Logger log = LoggerFactory.getLogger(DemoPathPostServlet.class);



    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        Session session = workflowSession.adaptTo(Session.class);
        String payload = (String) workItem.getWorkflow().getWorkflowData().getPayload();
        Node node = null;
        try {
            node = session.getNode("/content");

            String[] argument = metaDataMap.get("PROCESS_ARGS", String.class).split(",");
            for (int i = 0; i < argument.length; i++) {
                String[] dialogvalues = argument[i].split(":");
                String key = dialogvalues[0];
                String value = dialogvalues[1];
                node.setProperty(key, value);
                log.info("fields name value fetch:{}:{}-->", key, value);
            }
        }
            catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
}
