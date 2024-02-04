package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.CustomOsgiService;
import org.osgi.service.component.annotations.Component;

@Component(service = CustomOsgiService.class)
public class CustomOsgiServiceImpl implements CustomOsgiService {

    @Override
    public String getValue() {
        return "Aaqib-Khan-aaqib123@gmail.com";
    }
}
