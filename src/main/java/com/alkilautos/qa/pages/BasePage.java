package com.alkilautos.qa.pages;

import com.alkilautos.qa.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

  protected WebDriver driver;
  protected Wait<WebDriver> wait;
  private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

  public BasePage(WebDriver driver) {

    this.driver = driver;
    this.wait = DriverManager.getWait();
    PageFactory.initElements(
        new AjaxElementLocatorFactory(driver, Integer.parseInt(System.getProperty("timeout"))),
        this);
  }

  public void click(WebElement element) {

    int attempts = 0;
    while (attempts <= 3) {
      try {
        waitToBeVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return;

      } catch (StaleElementReferenceException e) {
        logger.warn(
            "Unable to interact with web element {} due to StaleElementReferenceException, DOM was refreshed. Retrying...",
            element);
        attempts++;
      }
    }
    logger.error("Unable to interact with web element {}, attempts exceeded", element);
  }

  public void sendKeys(WebElement element, String text) {

    waitToBeVisible(element);
    element.sendKeys(text);
  }

  public String getText(WebElement element) {

    waitToBeVisible(element);
    return element.getText();
  }

  public void waitToBeVisible(WebElement element) {

    DriverManager.getWait().until(ExpectedConditions.visibilityOf(element));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void waitToBeInvisible(WebElement element) {

    DriverManager.getWait().until(ExpectedConditions.invisibilityOf(element));
  }
}
