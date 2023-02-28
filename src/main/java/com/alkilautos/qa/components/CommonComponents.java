package com.alkilautos.qa.components;

import com.alkilautos.qa.enums.Languages;
import com.alkilautos.qa.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonComponents extends BasePage {

  @FindBy(how = How.CSS, using = "[data-target='lenguajes']")
  private WebElement languageMenu;

  @FindBy(how = How.ID, using = "aceptar-cookies")
  private WebElement acceptCookiesButton;

  private final String LANGUAGE_ITEM_PATH = "[alt='%s']";

  public CommonComponents(WebDriver driver) {
    super(driver);
  }

  public void changeLanguage(Languages language) {

    click(languageMenu);
    WebElement languageItem =
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(String.format(LANGUAGE_ITEM_PATH, language.getLanguageName()))));
    click(languageItem);
  }

  public void acceptCookies() {
    click(acceptCookiesButton);
  }
}
