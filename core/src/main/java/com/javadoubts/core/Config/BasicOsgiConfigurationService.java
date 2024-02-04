package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name="Basic Osgi Configuration Service",
        description = "This is Basic Osgi Configuration Service"
)
public @interface BasicOsgiConfigurationService {
    @AttributeDefinition(
            name = "url endpoint",
            description = "url endpoint field"
    )
    String getUrlEndPoint();

    @AttributeDefinition(
            name="password",
            description = "password field"
    )
    String password();

}
