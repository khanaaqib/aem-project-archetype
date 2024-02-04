package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FileWriterService;
import org.osgi.service.component.annotations.Component;

import java.io.FileWriter;

@Component(service = FileWriterService.class)
public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public void getFile(String text) {
        try{
            FileWriter fWriter = new FileWriter(
                    "C:\\Users\\aaqib\\OneDrive\\Desktop\\demo.txt");
            fWriter.write(text);
            fWriter.close();
        } catch (Exception e){
            e.getMessage();
        }
    }
}
