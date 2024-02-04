package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Custom Scheduler Configuration",
        description = "This is Custom Scheduler Configuration"
)
public @interface CustomSchedulerServiceConfiguration {

    @AttributeDefinition(
            name="cron job",
            description = "this is cron job field"
    )
    String getCronJob();

    @AttributeDefinition(
            name= "Schduler name",
            description = "This is Scheduler Name field"
    )
    String getSchedulerName();
}
