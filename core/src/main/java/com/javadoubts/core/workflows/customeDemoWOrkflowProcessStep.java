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
        property = {"process.label= Custom WOrkflow Process Step"}
  )
public class customeDemoWOrkflowProcessStep implements WorkflowProcess {

    private Logger log = LoggerFactory.getLogger(customeDemoWOrkflowProcessStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
               String title = metaDataMap.get("title",String.class);
               log.info("title value check:{}");
    }
}
