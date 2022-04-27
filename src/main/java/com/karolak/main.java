package com.karolak;

import com.karolak.testing.config.WebDriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.karolak.testing.config.WebDriverConfig.CHROME;

public class main {
    public static void main(String[] args) {

        WebDriver driver;
        driver = WebDriverConfig.getDriver(CHROME);
        driver.manage().window().maximize();
        driver.get("https://www.nazwa.pl/zaloguj-sie");
        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginField.clear();
        passField.clear();
        loginField.sendKeys("");
        passField.sendKeys("");
        passField.submit();

        if (driver.switchTo().alert().getText().contains("")) {
            System.out.println("GITAAARAAA");
        }
        else {
            System.out.println("xxxx");
        }



    }
}


