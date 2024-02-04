package com.javadoubts.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = WorkflowProcess.class, property = {
        "process.label" + " = Training Custom Workflow Process"
})
public class TrainingWorkflowProcess implements WorkflowProcess {

    private Logger logger = LoggerFactory.getLogger(TrainingWorkflowProcess.class);


    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

             logger.info("<------------------------------------------------------------------->");
             String [] argumnents = metaDataMap.get("PROCESS_ARGS","string").split(",");
             for(String arg : argumnents){
                 String [] args = arg.split(":");
                 String properties = args[0];
                 String value = args[1];
                 logger.info("key value fetch:{}",properties);
                 logger.info("argument value check:{}",value);
             }
    }
}
