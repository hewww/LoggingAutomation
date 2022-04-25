package com.karolak.testing.google;

import com.karolak.testing.config.WebDriverConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import static com.karolak.testing.config.WebDriverConfig.CHROME;

@RunWith(JUnit4.class)
public class UnitTests {

    public WebDriver driver;
    public static final String XPATH_LOGIN = "//input[@id='clientLogin']";
    public static final String XPATH_PASS = "//input[@id='clientPass']";
    public static final String XPATH_RESULT = "//div//td[1]";
    public static final String XPATH_RESULT2 = "//input[@name= 'authenticationCode']";

    @Before
    public void initialize() {
        driver = WebDriverConfig.getDriver(CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.nazwa.pl/zaloguj-sie");
        driver.manage().window().maximize();
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));
        loginField.clear();
        passField.clear();
    }

    @After
    public void afterCase() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test
    public void testProperlyLogging() {

        //Given
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkris");
        passField.sendKeys("Adminos123!");
        passField.submit();

        //Assert
        Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT2)).isDisplayed());

    }
    @Test
    public void testWrongLogin() {

        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkrisssssss");
        passField.sendKeys("Adminos123!");
        passField.submit();

        //Assert
        Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT)).isDisplayed());
    }

    @Test
    public void testWrongPassword() {

        //When
        WebElement loginField = driver.findElement(By.xpath(XPATH_LOGIN));
        WebElement passField = driver.findElement(By.xpath(XPATH_PASS));

        //Then
        loginField.sendKeys("testingkris");
        passField.sendKeys("kugiyfgi");
        passField.submit();

        //Assert
            Assert.assertTrue(driver.findElement(By.xpath(XPATH_RESULT)).isDisplayed());
    }

    @Test
    public void testEmptySubmit() {

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
    }


}
