package com.example.playwright.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    public static void setUpClass() {
        // Initialize Playwright and Browser (only once for all tests)
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    public void setUpTest() {
        // Create a new browser context and page for each test
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    public void tearDownTest() {
        // Close the browser context after each test
        if (browserContext != null) {
            browserContext.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        // Close the browser and Playwright after all tests
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
