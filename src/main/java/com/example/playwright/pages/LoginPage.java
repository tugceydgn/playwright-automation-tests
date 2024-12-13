package com.example.playwright.pages;

import com.microsoft.playwright.Page;
import com.example.playwright.config.*;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage() {
        String baseUrl = TestConfig.getBaseUrl(); // URL TestConfig üzerinden alınıyor
        System.out.println("Navigating to login page: " + baseUrl);
        page.navigate(baseUrl);
    }

    public void login(String username, String password) {
        System.out.println("Waiting for username field...");
        page.locator("#username").waitFor();
        System.out.println("Username field found. Filling...");
        page.fill("#username", username);

        System.out.println("Waiting for password field...");
        page.locator("#password").waitFor();
        System.out.println("Password field found. Filling...");
        page.fill("#password", password);

        System.out.println("Clicking login button...");
        page.click("button[type='submit']");

        System.out.println("Waiting for 3 seconds after login...");
        page.waitForTimeout(3000);
    }

    public void loginIfNeeded(String username, String password) {
        // Giriş yapılmış mı kontrol et
        if (!page.isVisible("text=Logout") && !page.isVisible("text=Web Application")) {
            System.out.println("User is not logged in. Performing login...");
            login(username, password);
        } else {
            System.out.println("User is already logged in. Skipping login.");
        }
    }
}
