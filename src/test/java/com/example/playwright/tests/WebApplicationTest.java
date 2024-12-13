package com.example.playwright.tests;

import com.example.playwright.config.TestConfig;
import com.example.playwright.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebApplicationTest extends BaseTest {

    private void performLoginAndNavigate(String sectionButtonSelector) {
        LoginPage loginPage = new LoginPage(page);

        // Perform login
        System.out.println("Logging in...");
        loginPage.navigateToLoginPage();
        loginPage.loginIfNeeded(TestConfig.getUsername(), TestConfig.getPassword());

        // Navigate to the specified section
        System.out.println("Navigating to section...");
        page.locator(sectionButtonSelector).click();
        page.waitForTimeout(2000); // Wait for the page to load
    }

    private void verifyColumnTitle(String columnSelector, String expectedTitle) {
        System.out.println("Verifying column title...");
        String actualTitle = page.locator(columnSelector).textContent().trim();
        String cleanTitle = actualTitle.split(" \\(")[0]; // Extract title without count
        Assertions.assertEquals(expectedTitle, cleanTitle, String.format("Expected column title '%s' but found '%s'", expectedTitle, cleanTitle));
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
    public void verifyToDoColumnInWebApplication() {
        performLoginAndNavigate("nav button:first-of-type");

        // Verify "To Do" column
        verifyColumnTitle("main > div > div > div:nth-of-type(1) > h2", "To Do");

        // Verify "Implement User Authentication" task
        verifyTask("main > div > div > div:nth-of-type(1) > div > div:nth-of-type(1) > h3", "Implement user authentication");

        // Verify "Feature" tag
        verifyTag("main > div > div > div:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(1) > span:nth-of-type(1)", "Feature");

        // Verify "High Priority" tag
        verifyTag("main > div > div > div:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(1) > span:nth-of-type(2)", "High Priority");

        System.out.println("Test Case 1 passed: 'To Do' column, task, and tags verified successfully.");
    }

    @Test
    public void verifyFixNavigationBugTaskInWebApplication() {
        performLoginAndNavigate("nav button:first-of-type");

        // Verify "To Do" column
        verifyColumnTitle("main > div > div > div:nth-of-type(1) > h2", "To Do");

        // Verify "Fix Navigation Bug" task
        verifyTask("main > div > div > div:nth-of-type(1) > div > div:nth-of-type(2) > h3", "Fix navigation bug");

        // Verify "Bug" tag
        verifyTag("main > div > div > div:nth-of-type(1) > div > div:nth-of-type(2) > div:nth-of-type(1) > span", "Bug");

        System.out.println("Test Case 2 passed: 'Fix Navigation Bug' task and 'Bug' tag verified successfully.");
    }

    @Test
    public void verifyDesignSystemUpdatesInProgress() {
        performLoginAndNavigate("nav button:first-of-type");

        // Verify "In Progress" column
        verifyColumnTitle("main > div > div > div:nth-of-type(2) > h2", "In Progress");

        // Verify "Design System Updates" task
        verifyTask("main > div > div > div:nth-of-type(2) > div > div > h3", "Design system updates");

        // Verify "Design" tag
        verifyTag("main > div > div > div:nth-of-type(2) > div > div > div:first-of-type > span", "Design");

        System.out.println("Test Case 3 passed: 'Design System Updates' task and 'Design' tag in 'In Progress' column verified successfully.");
    }
}
