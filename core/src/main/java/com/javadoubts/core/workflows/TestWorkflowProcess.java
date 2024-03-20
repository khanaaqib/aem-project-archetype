package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Test Workflow Process"}
)
public class TestWorkflowProcess implements WorkflowProcess {

    private Logger logger= LoggerFactory.getLogger(TestWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        logger.info("workflow initiated");
        String title = metaDataMap.get("title","not set");
        String payload = workItem.getWorkflowData().getPayload().toString()+ "/jcr:content";
        Session session = workflowSession.adaptTo(Session.class);
        try {
            Node node = session.getNode(payload);
            node.setProperty("title",title);
            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        logger.info("title value check::::{}",title);
    }
}
