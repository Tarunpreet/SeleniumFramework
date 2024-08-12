package Org.Framework.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;

public class JsonUtil {

    // Generic method to deserialize JSON from a file
    public static <T> T deserializationToClass(String fileName, Class<T> targetClass)  {
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
    //sample-> new TypeReference<List<Hashmap<string,string>>> ()
    public static <T> T deserialization(String fileName, TypeReference<T> obj)  {
        String pathToFile=System.getProperty("user.dir")+"/src/main/resources/"+fileName+".json";
        try{
            File file = new File(pathToFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(fileInputStream, obj);
        } catch (Exception e) {
            return null;
        }

    }
}