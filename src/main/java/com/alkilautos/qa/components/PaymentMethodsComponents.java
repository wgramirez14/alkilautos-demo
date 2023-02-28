package com.alkilautos.qa.components;

import com.alkilautos.qa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PaymentMethodsComponents extends BasePage {

  @FindBy(how = How.XPATH, using = "//span[text()=\"I'M IN TOWN\"]")
  private WebElement imInTownRadioButton;

  @FindBy(how = How.ID, using = "cardNumber")
  private WebElement cardNumberInput;

  @FindBy(how = How.ID, using = "creditCardMonth")
  private WebElement dueDateMonthInput;

  @FindBy(how = How.ID, using = "creditCardYear")
  private WebElement dueDateYearInput;

  @FindBy(how = How.ID, using = "cardCsc")
  private WebElement cvcCodeInput;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Pay at Counter')]")
  private WebElement payAtCounterRadioButton;

  @FindBy(how = How.XPATH, using = "//span[text()='I accept the']")
  private WebElement terms1CheckBox;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'I understand')]")
  private WebElement terms2CheckBox;

  @FindBy(how = How.ID, using = "btn-reservar")
  private WebElement reserveNowButton;

  @FindBy(how = How.CSS, using = ".mensaje > span")
  private WebElement successReserveMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Completing your booking')]")
  private WebElement completingBookingMessage;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Go to Booking Status')]")
  private WebElement goToBookingStatusButton;

  public PaymentMethodsComponents(WebDriver driver) {
    super(driver);
  }

  public void fillOutPaymentMethod() {
    click(imInTownRadioButton);
    click(payAtCounterRadioButton);
    click(terms1CheckBox);
    click(terms2CheckBox);
  }

  public String reserveCar() {
    click(reserveNowButton);
    waitToBeInvisible(completingBookingMessage);
    waitToBeVisible(successReserveMessage);
    return getText(successReserveMessage);
  }

  public void goToMyReserve() {
    click(goToBookingStatusButton);
  }
}
