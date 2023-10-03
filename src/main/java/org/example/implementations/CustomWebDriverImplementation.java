package org.example.implementations;

import org.example.drivers.WebDriverInterface;
import org.example.enums.BrowserType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CustomWebDriverImplementation implements WebDriverInterface {

    private WebDriver driver;
    private WebDriverWait wait;

    private Duration duration;

    @Override
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public Object executeScript(String script, Object... args) {
        if (driver instanceof JavascriptExecutor js) {
            return js.executeScript(script, args);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }
    }

    @Override
    public void dragAndDrop(String sourceSelector, String targetSelector) {
        WebElement sourceElement = driver.findElement(By.cssSelector(sourceSelector));
        WebElement targetElement = driver.findElement(By.cssSelector(targetSelector));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    @Override
    public void hover(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    @Override
    public void selectByText(String cssSelector, String text) {
        WebElement dropdown = driver.findElement(By.cssSelector(cssSelector));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    @Override
    public void selectByValue(String cssSelector, String value) {
        WebElement dropdown = driver.findElement(By.cssSelector(cssSelector));
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    @Override
    public void selectByIndex(String cssSelector, int index) {
        WebElement dropdown = driver.findElement(By.cssSelector(cssSelector));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    @Override
    public int getElementCount(String cssSelector) {
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
        return elements.size();
    }

    @Override
    public String getAttribute(String cssSelector, String attributeName) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        return element.getAttribute(attributeName);
    }

    @Override
    public String getCssValue(String cssSelector, String cssProperty) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        return element.getCssValue(cssProperty);
    }

    @Override
    public WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type");
        }

        duration = Duration.ofSeconds(10);
        wait = new WebDriverWait(driver, duration); // Initialize wait
        return driver;
    }
    public CustomWebDriverImplementation(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void goTo(String url) {
        driver.get(url);
    }

    @Override
    public void click(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        element.click();
    }

    @Override
    public void type(String cssSelector, String text) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        element.sendKeys(text);
    }

    @Override
    public String getText(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        return element.getText();
    }

    @Override
    public boolean isDisplayed(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        return element.isDisplayed();
    }

    @Override
    public void closeBrowser() {
        driver.quit();
    }

    @Override
    public void goBack() {
        driver.navigate().back();
    }

    @Override
    public void goForward() {
        driver.navigate().forward();
    }

    @Override
    public void refresh() {
        driver.navigate().refresh();
    }

    @Override
    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    @Override
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    @Override
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    @Override
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    @Override
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    @Override
    public void waitForElementVisible(String cssSelector, int timeoutInSeconds) {
        duration = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait customWait = new WebDriverWait(driver, duration);
        customWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    @Override
    public void waitForElementClickable(String cssSelector, int timeoutInSeconds) {
        duration = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait customWait = new WebDriverWait(driver, duration);
        customWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
    }

    @Override
    public void waitForElementHidden(String cssSelector, int timeoutInSeconds) {
        duration = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait customWait = new WebDriverWait(driver, duration);
        customWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    @Override
    public void takeScreenshot(String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(source.toPath(), new File(filePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAllCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }

    @Override
    public void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    @Override
    public void doubleClick(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    @Override
    public void scrollToElement(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    @Override
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
