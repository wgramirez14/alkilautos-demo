package com.alkilautos.qa.pages;

import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {

  private final WebDriver driver;
  private IndexPage indexPage;

  public PageManager(WebDriver driver) {

    this.driver = driver;
  }

  public IndexPage getIndexPage() {
    return Objects.requireNonNullElseGet(
        indexPage, () -> indexPage = PageFactory.initElements(driver, IndexPage.class));
  }
}
