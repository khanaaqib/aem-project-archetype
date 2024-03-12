package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = WorkflowProcess.class,
        property= {"process.label= Custom Demo Workflow Process"}
)

public class CustomDemoWorkflowProcess implements WorkflowProcess {

    private Logger logger = LoggerFactory.getLogger(CustomDemoWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        WorkflowData workflowData = workItem.getWorkflowData();
        String payload = workflowData.getPayload().toString()+"/jcr:content";
        Session session = workflowSession.adaptTo(Session.class);
        try {
        Node node = (Node) session.getItem(payload);
        //Node childNode = node.addNode("argument-value","nt:unstructured");
        String [] argument = metaDataMap.get("PROCESS_ARGS",String.class).split(",");
        for(int i =0; i<argument.length; i++){
            String [] dialogValue = argument[i].split(":");
            String key = dialogValue[0];
            String value = dialogValue[1];
            node.setProperty(key,value);
            String nodeName = node.getName();
            Node childeNode = node.addNode("data-node","nt:unstructured");
            logger.info("key value check:{},value checl:{}",key,value);
        }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
}
