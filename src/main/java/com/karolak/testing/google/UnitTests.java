package com.karolak.testing.google;

import com.karolak.testing.config.WebDriverConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;


public class UnitTests {

    public static final String XPATH_LOGIN = "//input[@id='clientLogin']";
    public static final String XPATH_PASS = "//input[@id='clientPass']";
    public static final String XPATH_RESULT = "//div//td[1]";
    public static final String XPATH_RESULT2 = "//input[@name= 'authenticationCode']";

    @Test
    void testProperlyLogging() {

        //Given
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.nazwa.pl/zaloguj-sie");

        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkris");
        passField.sendKeys("Adminos123!");
        passField.submit();

        //Assert
        try {
            Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT2)).isDisplayed());
        } catch (Exception e) {
            Assert.fail("Test Failed");
        }
        driver.close();

    }

    @Test
    void testWrongLogin() {
        //Given
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.nazwa.pl/zaloguj-sie");


        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkrisssssss");
        passField.sendKeys("Adminos123!");
        passField.submit();

        //Assert
        try {
            Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT)).isDisplayed());
        } catch (Exception e) {
            Assert.fail("Test failed");
        }
        driver.close();

    }

    @Test
    void testWrongPassword() {
        //Given
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.nazwa.pl/zaloguj-sie");


        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkris");
        passField.sendKeys("kugiyfgi");
        passField.submit();

        //Assert
        try {
            Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT)).isDisplayed());
        } catch (Exception e) {
            Assert.fail("Test failed");
        }
        driver.close();
    }

    @Test
    void testEmptySubmit() {

        //Given
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.nazwa.pl/zaloguj-sie");

        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("");
        passField.sendKeys("");
        passField.submit();


        //Assert
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            Assert.assertTrue(passField.isDisplayed() & loginField.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Test Failed, propably captcha enabled");
        }
        driver.close();
    }
}
