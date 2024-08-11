package Org.Framework.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonUtil {

    // Generic method to deserialize JSON from a file
    public static <T> T deserialization(String fileName, Class<T> targetClass)  {
        String pathToFile=System.getProperty("user.dir")+"/src/main/resources/"+fileName+".json";
        try{
        File file = new File(pathToFile);

         FileInputStream fileInputStream = new FileInputStream(file);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(fileInputStream, targetClass);
        } catch (Exception e) {
            return null;
        }

    }
}