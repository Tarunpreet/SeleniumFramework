package Org.Framework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public Properties propertiesLoader(String filename)
    {
        String path=System.getProperty("user.dir")+"/src/main/resources/"+filename+".properties";
        File propFile=new File(path);
        FileInputStream propStream;
        try {
             propStream=new FileInputStream(propFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties prop=new Properties();
        try {
            prop.load(propStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  prop;
    }
}
