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
    property = {"process.label= Demo Workflow"}
)
public class CustomAEMWorkflowProcess implements WorkflowProcess {
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
            String payload = workItem.getWorkflowData().getPayload().toString()+"/jcr:content";
            Session session = workflowSession.adaptTo(Session.class);
        try {
            Node node = session.getNode(payload);
            node.setProperty("email","aaqibkhan.3may@gmail.com");
            node.setProperty("contact","8888887863");
            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
}
