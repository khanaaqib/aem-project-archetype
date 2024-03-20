package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.javadoubts.core.services.FetchResourceService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import javax.servlet.Servlet;
import java.util.Iterator;

@Component(service = WorkflowProcess.class,
        property = {"process.label= Workflow to publish Pages"}
)
public class WorkflowPublishPage implements WorkflowProcess {

    @Reference
    Replicator replicator;

    @Reference
    FetchResourceService fetchResourceService;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
       String payload = workItem.getWorkflowData().getPayload().toString();
        ResourceResolver resourceResolver = null;
        try {
            resourceResolver = fetchResourceService.getResourceResolver();
            Session session = workflowSession.adaptTo(Session.class);
            Iterator<Resource> itr = resourceResolver.getResource(payload).listChildren();
            while (itr.hasNext()){
                Resource resource = itr.next();
                Page page = resource.adaptTo(Page.class);
                String childPagePath = page.getPath();
                replicator.replicate(session, ReplicationActionType.ACTIVATE, childPagePath);
            }
        }catch (ReplicationException e) {
                throw new RuntimeException(e);
            } catch (LoginException e) {
                throw new RuntimeException(e);
            }
        }




    }

