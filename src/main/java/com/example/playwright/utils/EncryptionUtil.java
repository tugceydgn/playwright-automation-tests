package com.example.playwright.utils;

import java.util.Base64;

public class EncryptionUtil {
    public static String decode(String encodedValue) {
        return new String(Base64.getDecoder().decode(encodedValue));
    }
}
