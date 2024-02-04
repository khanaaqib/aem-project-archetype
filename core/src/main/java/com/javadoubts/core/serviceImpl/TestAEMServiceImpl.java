package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.TestAEMService;
import org.osgi.service.component.annotations.Component;

@Component(service = TestAEMService.class)
public class TestAEMServiceImpl implements TestAEMService {
    @Override
    public String getUserInfo() {
        return "Aaqib Khan";
    }
}
