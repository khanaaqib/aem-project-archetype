package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FirstAEMService;
import org.osgi.service.component.annotations.Component;

@Component(service = FirstAEMService.class)
public class FirstAEMServiceImpl implements FirstAEMService {

    @Override
    public String getAddress() {
        return "Pune,Viman Nagar";
    }
}
