package com.alkilautos.qa.pages;

import com.alkilautos.qa.components.ContactInformationComponents;
import com.alkilautos.qa.components.PaymentMethodsComponents;
import com.alkilautos.qa.dtos.ContactInformation;
import org.openqa.selenium.WebDriver;

public class ConfirmReservationPage extends BasePage {

  private final ContactInformationComponents contactInformationComponents;
  private final PaymentMethodsComponents paymentMethodsComponents;

  public ConfirmReservationPage(WebDriver driver) {
    super(driver);
    this.contactInformationComponents = new ContactInformationComponents(driver);
    this.paymentMethodsComponents = new PaymentMethodsComponents(driver);
  }

  public String reserveCar(ContactInformation contactInformation) {
    contactInformationComponents.fillOutContactInfo(contactInformation);
    paymentMethodsComponents.fillOutPaymentMethod();
    return paymentMethodsComponents.reserveCar();
  }

  public MyReservationPage goToMyReserve() {
    paymentMethodsComponents.goToMyReserve();
    return new MyReservationPage(driver);
  }
}
