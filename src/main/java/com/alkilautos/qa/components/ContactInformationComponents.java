package com.alkilautos.qa.components;

import com.alkilautos.qa.dtos.ContactInformation;
import com.alkilautos.qa.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

@Getter
public class ContactInformationComponents extends BasePage {

  @FindBy(how = How.ID, using = "name")
  private WebElement nameOfTheRenterInput;

  @FindBy(how = How.ID, using = "lastname")
  private WebElement lastNameInput;

  @FindBy(how = How.ID, using = "fecha-nac")
  private WebElement dateBirthInput;

  @FindBy(how = How.ID_OR_NAME, using = "tdoc")
  private WebElement documentTypeDropdown;

  @FindBy(how = How.ID_OR_NAME, using = "ndoc")
  private WebElement documentNumberInput;

  @FindBy(how = How.ID_OR_NAME, using = "tel2")
  private WebElement mobilePhoneInput;

  @FindBy(how = How.ID_OR_NAME, using = "email")
  private WebElement emailInput;

  @FindBy(how = How.ID_OR_NAME, using = "dir")
  private WebElement addressInput;

  @FindBy(how = How.ID_OR_NAME, using = "ciudad")
  private WebElement cityInput;

  public ContactInformationComponents(WebDriver driver) {
    super(driver);
  }

  public void fillOutContactInfo(ContactInformation contactInformation) {
    sendKeys(nameOfTheRenterInput, contactInformation.getNameOfTheRenter());
    sendKeys(lastNameInput, contactInformation.getLastName());
    sendKeys(dateBirthInput, contactInformation.getDateBirth());
    Select select = new Select(documentTypeDropdown);
    select.selectByVisibleText(contactInformation.getDocumentType().getDocumentTypeName());
    sendKeys(documentNumberInput, contactInformation.getDocumentNumber());
    sendKeys(mobilePhoneInput, contactInformation.getMobilePhone());
    sendKeys(emailInput, contactInformation.getEmail());
    sendKeys(addressInput, contactInformation.getAddress());
    sendKeys(cityInput, contactInformation.getCity());
  }
}
