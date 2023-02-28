package com.alkilautos.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {
  @Override
  protected void createDriver() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    options.addArguments("--headless");
    options.addArguments("--start-maximized");
    options.addArguments("--window-size=1920x1080");
    options.addArguments("--disable-javascript");
    super.driver = WebDriverManager.chromedriver().capabilities(options).create();
  }
}
