package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.Config.RevisedConfigService;
import com.javadoubts.core.services.RevisedService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import javax.jcr.Session;
import java.io.File;
import java.util.Scanner;

@Component(service = RevisedService.class)
@Designate(ocd= RevisedConfigService.class)
public class RevisedServiceImpl implements RevisedService {

    private RevisedConfigService configService;

    String path = "C:\\Users\\aaqib\\OneDrive\\Desktop\\first.txt";
    @Override
    public String getReadFile() {
        File file = new File(path);
        try {
            Session session =null;
            Scanner sc = new Scanner(file);
                return sc.nextLine();
        } catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Activate
    protected void activate(final RevisedConfigService configService){
        this.configService= configService;
    }

    @Override
    public String getClientId() {
        return configService.getClientId();
    }

    @Override
    public String getClientSecret() {
        return configService.getClientSecret();
    }
}
