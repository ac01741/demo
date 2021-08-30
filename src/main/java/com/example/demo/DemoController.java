package com.example.demo;

import com.ctl.esec.crypto.PropertyEncryptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Configuration
@PropertySource(value = "classpath:nara.properties")
@Profile("dev")
public class DemoController {
    private  HashMap<String, String> properties;

    @PostConstruct
    public void decriptPro() {
        String showError = "Key was not found";
        try {
            System.out.println("decriptPro");
            ResourceBundle bundle = ResourceBundle.getBundle("nara");
            PropertyEncryptor propEncryptor = new PropertyEncryptor("nara.properties");
            Enumeration<String> enumeration = bundle.getKeys();
            properties = new HashMap<String, String>();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = bundle.getString(key);
                System.out.println("Key - " + key);
                System.out.println("Value - " + value);
                String value2 = propEncryptor.getSensitiveProperty(key, showError);
                System.out.println("Value of " + key + " " + value2);
                properties.put(key, value2);
            }
            System.out.println("Properties - " + properties.toString());
        } catch (final MissingResourceException mre) {
            System.out.println("Error While Loading config file, File " + mre);
        } catch (final Exception e) {
            System.out.println("Error While Reading from config file, IOException:");
        }
    }

    public String getPropertyKeyValue(String key) {
        key = noNull(key);
        final String strIntValue = this.properties.get(key);
        return strIntValue;
    }

    public final String noNull(String string) {
        return noNull(string, "");
    }

    public final String noNull(String string, String defaultString) {
        if (string == null) {
            return defaultString;
        } else
            return string;
    }
}
