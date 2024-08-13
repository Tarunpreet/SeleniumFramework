package Org.Framework.Utils;

import EcommerceWeb.Pages.ConfirmPage;

import java.util.Properties;

public class ConfigManager {
    //SingleTon Design
    private final Properties prop;
    private static ConfigManager Instance;
    private ConfigManager()
    {
        PropertiesUtils laodInstance=new PropertiesUtils();
        prop=laodInstance.propertiesLoader("Config");
    }
    public static ConfigManager getInstance()
    {
        ConfigManager Instance=new ConfigManager();
        return Instance;
    }
    public String getStringProperty(String key)
    {
        return (String) prop.get(key);
    }
    public Integer getIntegerProperty(String key)
    {
        return (Integer) prop.get(key);
    }
    public Boolean getBooleanProperty(String key)
    {
        return (Boolean) prop.get(key);
    }

}
