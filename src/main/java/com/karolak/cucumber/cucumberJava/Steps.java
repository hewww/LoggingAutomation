package com.karolak.cucumber.cucumberJava;

import com.karolak.testing.config.WebDriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

import static com.karolak.testing.config.WebDriverConfig.CHROME;

public class Steps {

    WebDriver driver;
    WebElement passField;

    @Before
    public void setup() {
        driver = WebDriverConfig.getDriver(CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("^Open nazwa.pl website$")
    public void browserOpen() {
        driver.get("https://www.nazwa.pl/zaloguj-sie");
    }

    @When("^Input properly pass$")
    public void inputProperPass() {
        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        loginField.clear();
        passField.clear();
        loginField.sendKeys("testingkris");
        passField.sendKeys("Adminos123!");
        passField.submit();
    }

    @When("^Input wrong login with good password$")
    public void inputBadLoginWithProperPassword() {
        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginField.clear();
        passField.clear();
        loginField.sendKeys("testingkriss");
        passField.sendKeys("Adminos123!");
        passField.submit();
    }

    @When("^Input proper login with bad password$")
    public void inputProperLoginWithBadPassword() {
        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        loginField.sendKeys("testingkris");
        passField.sendKeys("kugiyfgi");
        passField.submit();
    }

    @When("^Empty input box submit$")
    public void emptySubmit() {
        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        loginField.sendKeys("");
        passField.sendKeys("");
        passField.submit();
    }

    @When("^Input password with bad letter length and proper login")
    public void testPasswordLetterLength() {

        WebElement loginField = driver.findElement(By.xpath("//input[@id='clientLogin']"));
        WebElement passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        loginField.sendKeys("testingkris");
        passField.sendKeys("adminos123!");

        passField.submit();
    }


    @Then("^Check properly login$")
    public void properAssert()
    {
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name= 'authenticationCode']")).isDisplayed());
    }

    @Then("^Check login gone wrong$")
    public void wrongAssert(){

        Assert.assertTrue(driver.findElement(By.xpath("//div//td[1]")).isDisplayed());
    }

    @Then("^Check alert is displayed")
    public void wrongAssertAlert(){
        driver.switchTo().alert().accept();
        passField = driver.findElement(By.xpath("//input[@id='clientPass']"));
        Assert.assertTrue(passField.isDisplayed());
    }

    @After
    public void clean(){
            driver.manage().deleteAllCookies();
            driver.close();
    }






}
