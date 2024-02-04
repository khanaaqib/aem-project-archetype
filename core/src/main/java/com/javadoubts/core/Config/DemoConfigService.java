package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Demo Config Service",
        description = "This is demo config service"
)
public @interface DemoConfigService {
    @AttributeDefinition(
            name="First Name",
            description = "This is First Name"
    )
    String getFirstName();

    @AttributeDefinition(
            name = "Last Name",
            description = "This is last name"
    )
    String getLastName();


    @AttributeDefinition(
            name = "email",
            description = "This is email"
    )
    String[] getEmail();
}
