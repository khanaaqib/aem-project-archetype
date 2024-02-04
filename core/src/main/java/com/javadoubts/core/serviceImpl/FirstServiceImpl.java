package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FirstService;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component(service = FirstService.class)
public class FirstServiceImpl implements FirstService {
  String cielntid="7wghjnFDRDREDNJ";
    @Override
    public String getFileInput() throws FileNotFoundException {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\first.txt");
        try {
            Session session =null;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
                     return sc.nextLine();
        } catch (Exception e){
            e.getMessage();
        }
        return null;
    }


}
