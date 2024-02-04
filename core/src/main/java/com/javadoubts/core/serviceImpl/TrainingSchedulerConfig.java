package com.javadoubts.core.serviceImpl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Training Schduler COnfiguration",
        description = "This is Training Configuration"
)
public @interface TrainingSchedulerConfig {
    @AttributeDefinition(
            name="CRON JOB",
            description = "Crob Job field"
    )
    String getCronJob();

    @AttributeDefinition(
            name="Scheduler Name",
            description = "This is scheduler name field"
    )
    String getShchedulerName();
}
