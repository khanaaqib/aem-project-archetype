package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = WorkflowProcess.class,
        property = {"process.label= Simple AEM Workflow Process"}
)
public class SimpleAEMWorkflowProcess  implements WorkflowProcess {

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        Session session = workflowSession.adaptTo(Session.class);
        try {
            String payload = workItem.getWorkflowData().getPayload().toString() + "/jcr:content";
            Node node = session.getNode(payload);
            node.setProperty("firstName","Aaqib");
            node.setProperty("lastName","Khan");
            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
