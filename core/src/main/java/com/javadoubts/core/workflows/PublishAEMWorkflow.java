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
import com.javadoubts.core.services.GetServiceUser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.Iterator;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Publish AEM Workflow"}
)
public class PublishAEMWorkflow implements WorkflowProcess {

    private Logger logger = LoggerFactory.getLogger(PublishAEMWorkflow.class);

    @Reference
    Replicator replicator;

    @Reference
    FetchResourceService serviceUser;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
           String payload = workItem.getWorkflowData().getPayload().toString();
           logger.info("payload value check:{}",payload);
           ResourceResolver resourceResolver= null;
        try {
           resourceResolver = serviceUser.getResourceResolver();
           logger.info("resorceResolver value check:{}",resourceResolver);
           Session session = resourceResolver.adaptTo(Session.class);
           Iterator<Resource> itr = resourceResolver.getResource(payload).listChildren();
           while (itr.hasNext()) {
               Resource resource = itr.next();
               String childPage = resource.getPath();
               logger.info("page value check:{}", childPage);
               replicator.replicate(session, ReplicationActionType.ACTIVATE, childPage);
             }
           }catch (ReplicationException | LoginException e) {
               throw new RuntimeException(e);
           }
           }
    }
