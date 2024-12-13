package com.example.playwright.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import com.example.playwright.utils.EncryptionUtil;

public class Config {
    public static String getUsername() {
        return getDecodedCredential("username");
    }

    public static String getPassword() {
        return getDecodedCredential("password");
    }

    public static String getBaseUrl() {
        return getDecodedCredential("baseUrl");
    }

    private static String getDecodedCredential(String key) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/data/credentials.json");
            JSONObject config = (JSONObject) parser.parse(reader);

            String encodedValue = (String) config.get(key);
            return EncryptionUtil.decode(encodedValue); // Base64 çözme işlemi
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
