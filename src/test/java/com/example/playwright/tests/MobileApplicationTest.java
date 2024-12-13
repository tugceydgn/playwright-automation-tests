package com.example.playwright.tests;

import com.example.playwright.config.TestConfig;
import com.example.playwright.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MobileApplicationTest extends BaseTest {

    private void performLoginAndNavigate(String sectionButtonSelector) {
        LoginPage loginPage = new LoginPage(page);

        // Perform login
        System.out.println("Logging in...");
        loginPage.navigateToLoginPage();
        loginPage.loginIfNeeded(TestConfig.getUsername(), TestConfig.getPassword());

        // Navigate to the specified section
        System.out.println("Navigating to the section...");
        page.locator(sectionButtonSelector).click();
        page.waitForTimeout(2000); // Wait for the page to load
    }

    private void verifyColumnTitle(String columnSelector, String expectedTitle) {
        System.out.println("Verifying column title...");
        String actualTitle = page.locator(columnSelector).textContent().trim();
        Assertions.assertTrue(actualTitle.contains(expectedTitle), String.format("Expected column title '%s' not found!", expectedTitle));
    }

    private void verifyTask(String taskSelector, String expectedTask) {
        System.out.println("Verifying task...");
        Assertions.assertTrue(page.locator(taskSelector).isVisible(), String.format("Task '%s' not found!", expectedTask));
        Assertions.assertEquals(expectedTask, page.locator(taskSelector).textContent().trim(), String.format("Task text mismatch for '%s'!", expectedTask));
    }

    private void verifyTag(String tagSelector, String expectedTag) {
        System.out.println("Verifying tag...");
        Assertions.assertTrue(page.locator(tagSelector).isVisible(), String.format("Tag '%s' not found!", expectedTag));
        Assertions.assertEquals(expectedTag, page.locator(tagSelector).textContent().trim(), String.format("Tag text mismatch for '%s'!", expectedTag));
    }

    @Test
    public void verifyPushNotificationSystemInMobileApplication() {
        performLoginAndNavigate("nav button:nth-of-type(2)");

        // Verify "To Do" column
        verifyColumnTitle("main > div > div > div:nth-of-type(1) > h2", "To Do");

        // Verify "Push Notification System" task
        verifyTask("main > div > div > div:nth-of-type(1) > div > div > h3", "Push notification system");

        // Verify "Feature" tag
        verifyTag("main > div > div > div:nth-of-type(1) > div > div > div > span", "Feature");

        System.out.println("Test Case 4 passed.");
    }

    @Test
    public void verifyOfflineModeInProgressColumn() {
        performLoginAndNavigate("nav button:nth-of-type(2)");

        // Verify "In Progress" column
        verifyColumnTitle("main > div > div > div:nth-of-type(2) > h2", "In Progress");

        // Verify "Offline Mode" task
        verifyTask("main > div > div > div:nth-of-type(2) > div > div > h3", "Offline mode");

        // Verify "Feature" tag
        verifyTag("main > div > div > div:nth-of-type(2) > div > div > div:nth-of-type(1) > span:nth-of-type(1)", "Feature");

        // Verify "High Priority" tag
        verifyTag("main > div > div > div:nth-of-type(2) > div > div > div:nth-of-type(1) > span:nth-of-type(2)", "High Priority");

        System.out.println("Test Case 5 passed.");
    }

    @Test
    public void verifyAppIconDesignInDoneColumn() {
        performLoginAndNavigate("nav button:nth-of-type(2)");

        // Verify "Done" column
        verifyColumnTitle("main > div > div > div:nth-of-type(4) > h2", "Done");

        // Verify "App Icon Design" task
        verifyTask("main > div > div > div:nth-of-type(4) > div > div > h3", "App icon design");

        // Verify "Design" tag
        verifyTag("main > div > div > div:nth-of-type(4) > div > div > div:nth-of-type(1) > span", "Design");

        System.out.println("Test Case 6 passed.");
    }
}
