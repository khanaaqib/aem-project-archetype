package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
      name = "First Configuration",
      description = "This is first configuration"
)
public @interface FirstConfig {

    @AttributeDefinition(
            name = "First Name",
            description = "This is first name field"
    )
    String getFirstName();

    @AttributeDefinition(
            name="client id",
            description = "this is client id field"
    )
    String getClientId();

    @AttributeDefinition(
            name="LastName",
            description = "this is last name"
    )
    String getLastName();
}
