package com.alkilautos.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {
  @Override
  protected void createDriver() {

    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");
    options.addArguments("--width=1366");
    options.addArguments("--height=768");

    super.driver = WebDriverManager.firefoxdriver().capabilities(options).create();
  }
}
