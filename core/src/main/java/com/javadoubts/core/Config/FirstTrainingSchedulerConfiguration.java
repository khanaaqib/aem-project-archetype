package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "First Training Scheduler Configuration",
        description = "This is First Training Scheduler Configuration"
)
public @interface FirstTrainingSchedulerConfiguration {

    @AttributeDefinition(
            name = "timining to execute the scheduler",
            description = "time field"
    )
    String getSchedulerTime();


    @AttributeDefinition(
            name = "scheduler name",
            description = "this is scheduler name field"
    )
    String getSchedulerName();
}
