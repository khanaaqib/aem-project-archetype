package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.DemoService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.Scanner;

@Component(service = DemoService.class)
public class DemoServiceImpl implements DemoService {
    @Override
    public String getFileInfo() {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\aaqib.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        } catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
