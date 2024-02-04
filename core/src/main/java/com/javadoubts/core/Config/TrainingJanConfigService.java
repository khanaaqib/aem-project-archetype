package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Training Jan Configuration",
        description = "This is Training Jan Configuration"
)
public @interface TrainingJanConfigService {

    @AttributeDefinition(
            name="url end point",
            description = "this is url endpoint"
    )
    String getUrlEndpoint();

    @AttributeDefinition(
            name="client Id",
            description = "This is client id"
    )
    String clientId();


    @AttributeDefinition(
            name="client secret",
            description = "this is client secret"
    )
    String clientSecret();

}
