//Singleton Pattern

package Sigletone_Patterns;
import java.util.Properties;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Properties properties;


    private ConfigurationManager() {

        properties = new Properties();

        properties.setProperty("url", "http://example.com");
        properties.setProperty("username", "admin");
        properties.setProperty("password", "password");
    }


    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}


class SingletonPatternDemo {
    public static void main(String[] args) {

        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();

        System.out.println("URL: " + config1.getProperty("url"));
        System.out.println("Username: " + config1.getProperty("username"));

        config1.setProperty("username", "newAdmin");

        System.out.println("Modified Username: " + config2.getProperty("username"));

        if (config1 == config2) {
            System.out.println("Both configuration manager instances are the same.");
        } else {
            System.out.println("Configuration manager instances are different.");
        }
    }
}
