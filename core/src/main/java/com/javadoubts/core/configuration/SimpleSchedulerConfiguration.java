package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Simple Scheduler Configuration",
        description = "This is Simple Scheduler Configuration"
)
public @interface SimpleSchedulerConfiguration {
    @AttributeDefinition(
            name="Cron Job",
            description = "This is cron job field"
    )String getCronJob();

    @AttributeDefinition(
            name = "Scheduler Name",
            description = "This is Scheduler Name"
    )String getSchedulerName();

}
