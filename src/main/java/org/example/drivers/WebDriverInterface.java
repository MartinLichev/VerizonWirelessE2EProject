package org.example.drivers;
import org.example.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public interface WebDriverInterface {
    void quitDriver();

    WebDriver createDriver(BrowserType browserType);

    // Navigate to a URL
    void goTo(String url);

    // Click an element by its selector
    void click(String cssSelector);

    // Type text into an input field
    void type(String cssSelector, String text);

    // Fetch text from an element
    String getText(String cssSelector);

    // Check if an element is displayed
    boolean isDisplayed(String cssSelector);

    // Close the browser
    void closeBrowser();

    // Navigate back in the browser history
    void goBack();

    // Navigate forward in the browser history
    void goForward();

    // Refresh the current page
    void refresh();

    // Execute a JavaScript command
    Object executeScript(String script, Object... args);

    // Switch to a different frame
    void switchToFrame(String nameOrId);

    // Switch back to the default frame
    void switchToDefault();

    // Accept an alert dialog
    void acceptAlert();

    // Dismiss an alert dialog
    void dismissAlert();

    // Get alert dialog text
    String getAlertText();

    // Drag and drop an element to a target
    void dragAndDrop(String sourceSelector, String targetSelector);

    // Hover over an element
    void hover(String cssSelector);

    // Select an option in a dropdown by visible text
    void selectByText(String cssSelector, String text);

    // Select an option in a dropdown by value
    void selectByValue(String cssSelector, String value);

    // Select an option in a dropdown by index
    void selectByIndex(String cssSelector, int index);

    // Get the count of elements matching a selector
    int getElementCount(String cssSelector);

    // Get attribute value of an element
    String getAttribute(String cssSelector, String attributeName);

    // Get CSS value of an element
    String getCssValue(String cssSelector, String cssProperty);

    // Wait for an element to be visible
    void waitForElementVisible(String cssSelector, int timeoutInSeconds);

    // Wait for an element to be clickable
    void waitForElementClickable(String cssSelector, int timeoutInSeconds);

    // Wait for an element to be hidden
    void waitForElementHidden(String cssSelector, int timeoutInSeconds);

    // Take a screenshot and save it
    void takeScreenshot(String filePath);

    // Get all cookies as a string
    String getAllCookies();

    // Add a cookie
    void addCookie(String name, String value);

    // Delete a cookie
    void deleteCookie(String name);

    // Perform a double click on an element
    void doubleClick(String cssSelector);

    // Scroll to an element
    void scrollToElement(String cssSelector);

    // Scroll to the top of the page
    void scrollToTop();

    // Scroll to the bottom of the page
    void scrollToBottom();
}
