package com.karolak.testing.config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfig {
    public final static String CHROME = "CHROME_DRIVER";

    public static WebDriver getDriver(final String driver) {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium-driver\\Chrome\\chromedriver.exe");
        return new ChromeDriver();}
    WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
}
