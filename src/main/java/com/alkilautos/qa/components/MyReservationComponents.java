package com.alkilautos.qa.components;

import com.alkilautos.qa.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class MyReservationComponents extends BasePage {

  @FindBy(how = How.CSS, using = ".modificar")
  private WebElement desistReservationButton;

  @FindBy(how = How.CSS, using = "#form-cambio button")
  private WebElement acceptButton;

  @FindBy(how = How.XPATH, using = "//i[contains(text(),'Sending…')]")
  private WebElement sendingMessage;

  @FindBy(how = How.CSS, using = ".estado strong")
  private WebElement reservationStatusMessage;

  @FindBy(how = How.CSS, using = ".modelo > h1")
  private WebElement carName;

  public MyReservationComponents(WebDriver driver) {
    super(driver);
  }
}
