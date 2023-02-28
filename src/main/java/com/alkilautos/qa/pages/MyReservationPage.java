package com.alkilautos.qa.pages;

import com.alkilautos.qa.components.CommonComponents;
import com.alkilautos.qa.components.MyReservationComponents;
import com.alkilautos.qa.enums.Languages;
import org.openqa.selenium.WebDriver;

public class MyReservationPage extends BasePage {

  private final MyReservationComponents myReservationComponents;
  private final CommonComponents commonComponents;

  public MyReservationPage(WebDriver driver) {
    super(driver);
    this.myReservationComponents = new MyReservationComponents(driver);
    this.commonComponents = new CommonComponents(driver);
  }

  public String cancelReservation() {
    commonComponents.changeLanguage(Languages.ENGLISH);
    click(myReservationComponents.getDesistReservationButton());
    click(myReservationComponents.getAcceptButton());
    waitToBeInvisible(myReservationComponents.getSendingMessage());
    waitToBeVisible(myReservationComponents.getReservationStatusMessage());
    return getText(myReservationComponents.getReservationStatusMessage());
  }

  public String getCarName() {
    return getText(myReservationComponents.getCarName());
  }
}
