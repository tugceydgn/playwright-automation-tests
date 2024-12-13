package com.example.playwright.tests;

import com.example.playwright.config.TestConfig;
import com.example.playwright.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        super.setUpTest(); // Initialize browser context and page
        loginPage = new LoginPage(page); // Initialize LoginPage with the Page object
    }

    @Test
    public void testLogin() {
        performLogin();

        // Verify successful login by checking if "Web Application" is visible
        System.out.println("Verifying login success...");
        Assertions.assertTrue(page.isVisible("text=Web Application"), "After login, 'Web Application' is not visible!");

        System.out.println("Login test passed: User successfully logged in and 'Web Application' is visible.");
    }

    private void performLogin() {
        System.out.println("Navigating to Login Page...");
        loginPage.navigateToLoginPage();

        System.out.println("Performing login...");
        loginPage.loginIfNeeded(TestConfig.getUsername(), TestConfig.getPassword());
    }
}
