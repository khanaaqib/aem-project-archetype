package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Training Configuration",
        description = "This is training configuration"
)
public @interface TrainingConfigService {

    @AttributeDefinition(
            name = "First Name",
            description = "This is first name"
    )
    String getFirstName();

    @AttributeDefinition(
            name = "Last Name",
            description = "This is last name field"
    )
    String getLastName();


    @AttributeDefinition(
            name="phone number",
            description = "this is phone number field"
    )

    String[] getPhoneNumber();

}
