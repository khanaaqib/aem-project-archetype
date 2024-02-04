package com.javadoubts.core.Config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "FileScheduler Configuration",
        description = "This is FileScheduler"
)
public @interface FileSchedulerConfiguration {

    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Scheduler Name Field"
    )
    String getSchedulerName();


    @AttributeDefinition(
            name = "Cron JOb",
            description = "Cron Job Field"
    )
    String cronJOb();
}
