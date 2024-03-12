package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.CustoemDemoService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component(service = CustoemDemoService.class)
public class CustomDemoServiceImpl implements CustoemDemoService {

    @Override
    public String getFileInfo()  {
        // pass the path to the file as a parameter
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\Vijaytest.csv");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        }catch (FileNotFoundException e){
            e.getMessage();
        }
        return null;
    }
}
