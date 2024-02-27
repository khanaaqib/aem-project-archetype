package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name="Training Test Scheduler",
        description = "Training Test Scheduler"
)
public @interface TrainingTestSchedulerConfig {

    @AttributeDefinition(
            name = "crob job value",
            description = "crob job field"
    )String cronJob();

    @AttributeDefinition(
            name = "scheduler name",
            description = "scheduler name field"
    )String getSchedulerName();

}
