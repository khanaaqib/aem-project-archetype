package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Schduler Job COnfiguration",
                         description = "This is Schduler Job COnfiguration")
public @interface SchedulerJobConfiguration {
    @AttributeDefinition(
            name = "CRON JOB",
            description = "Cron Job field"
    )String cronJobValue();


    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Schduler Name field"
    )String shedulerNameValue();
}
