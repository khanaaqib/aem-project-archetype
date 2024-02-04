package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.BasicOsgiService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.Scanner;

@Component(service = BasicOsgiService.class)
public class BasicOsgiServiceImpl implements BasicOsgiService {

    @Override
    public String getUserData() {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\basicOsgi.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                 return sc.nextLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getMessage() {
        return "Heelo world";
    }
}
