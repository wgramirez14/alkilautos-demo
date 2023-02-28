package com.alkilautos.qa.driver;

import com.alkilautos.qa.enums.Browser;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DriverManager {
  private static final String BROWSER = "browser";
  private static final String BROWSER_NOT_SUPPORTED_MSG =
      "Invalid browser name, not supported yet: %s";
  private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
  protected WebDriver driver;
  private static Wait<WebDriver> wait;

  protected abstract void createDriver();

  public void quitDriver() {
    if (null != driver) {
      driver.close();
      driver.quit();
      driver = null;
    }
  }

  public WebDriver getDriver(String timeout, String pollingTime) {

    if (null == driver) {
      createDriver();

      wait =
          driver != null
              ? new FluentWait<>(driver)
                  .withTimeout(Duration.ofSeconds(Integer.parseInt(timeout)))
                  .pollingEvery(Duration.ofSeconds(Integer.parseInt(pollingTime)))
              : null;
    }

    return driver;
  }

  public static Wait<WebDriver> getWait() {
    return wait;
  }

  public static DriverManager getManager() {

    String browserName = System.getProperty(BROWSER);

    if (browserName.equalsIgnoreCase(Browser.CHROME.name())) {
      return new ChromeDriverManager();
    } else if (browserName.equalsIgnoreCase(Browser.FIREFOX.name())) {
      return new FirefoxDriverManager();
    } else {
      logger.error(String.format(BROWSER_NOT_SUPPORTED_MSG, browserName));
      throw new IllegalArgumentException(String.format(BROWSER_NOT_SUPPORTED_MSG, browserName));
    }
  }
}
