package com.alkilautos.qa.hooks;

import com.alkilautos.qa.driver.DriverManager;
import com.alkilautos.qa.pages.PageManager;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {

  private DriverManager driverManager;
  protected PageManager pageManager;
  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp(ITestContext context) {
    System.setProperty("timeout", context.getCurrentXmlTest().getParameter("timeout"));
    System.setProperty("browser", context.getCurrentXmlTest().getParameter("selenium.browser"));

    driverManager = DriverManager.getManager();
    driver =
        driverManager.getDriver(
            context.getCurrentXmlTest().getParameter("timeout"),
            context.getCurrentXmlTest().getParameter("pollingTime"));
    driver.get(context.getCurrentXmlTest().getParameter("selenium.url"));
    pageManager = new PageManager(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {

    driverManager.quitDriver();
  }

  protected void takeScreenshot(String name) {

    Allure.addAttachment(
        name,
        new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
  }
}
