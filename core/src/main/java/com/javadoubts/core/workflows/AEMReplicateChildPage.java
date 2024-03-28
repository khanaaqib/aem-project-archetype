package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.javadoubts.core.services.FetchResourceService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import java.util.Iterator;

@Component(service = WorkflowProcess.class,
        property = {"process.label= Replicate child page"}
)
public class AEMReplicateChildPage implements WorkflowProcess {

    @Reference
    Replicator replicator;

    @Reference
    FetchResourceService fetchResourceService;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
         String payload = workItem.getWorkflowData().getPayload().toString();
        try {
            ResourceResolver resourceResolver = fetchResourceService.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            Iterator<Resource> itr = resourceResolver.getResource(payload).listChildren();
            while (itr.hasNext()){
                Resource resource = itr.next();
                String childPage = resource.getPath();
                replicator.replicate(session, ReplicationActionType.ACTIVATE,childPage);
            }

        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (ReplicationException e) {
            throw new RuntimeException(e);
        }


    }
}
