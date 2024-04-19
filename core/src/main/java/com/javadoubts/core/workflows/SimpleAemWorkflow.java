package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Simple AEM Workflow"}
)
public class SimpleAemWorkflow implements WorkflowProcess {

    private Logger logger= LoggerFactory.getLogger(SimpleAemWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        String titleVlaue = metaDataMap.get("title",String.class);
        logger.info("workflow title value check:{}",titleVlaue);
    }
}
