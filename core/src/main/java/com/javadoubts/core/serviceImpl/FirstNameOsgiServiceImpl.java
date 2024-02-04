package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FirstNameOsgiService;
import org.osgi.service.component.annotations.Component;

@Component(service = FirstNameOsgiService.class)
public class FirstNameOsgiServiceImpl implements FirstNameOsgiService {
    @Override
    public String getFirstName() {
        return "Aaqib";
    }
}
