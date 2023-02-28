package com.alkilautos.qa.components;

import com.alkilautos.qa.dtos.SearchCriteriaInfo;
import com.alkilautos.qa.pages.BasePage;
import com.alkilautos.qa.utils.DataGenerator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class CarSearchComponents extends BasePage {

  private static final Logger logger = LoggerFactory.getLogger(CarSearchComponents.class);

  @FindBy(how = How.ID, using = "ciudadi-str")
  private WebElement pickupLocationInput;

  @FindBy(how = How.ID, using = "fechai-recogida")
  private WebElement pickUpDateInput;

  @FindBy(how = How.CSS, using = ".open  [aria-label='Month']")
  private WebElement monthDropdown;

  @FindBy(how = How.CSS, using = ".open .flatpickr-prev-month")
  private WebElement previousMonthPicker;

  @FindBy(how = How.CSS, using = ".open  cur-year")
  private WebElement yearField;

  @FindBy(how = How.ID, using = "fechaf-devolucion")
  private WebElement dropOffDateInput;

  @FindBy(how = How.ID, using = "hora-ini")
  private WebElement pickUpHourInput;

  @FindBy(how = How.ID, using = "hora-fin")
  private WebElement dropOffHourInput;

  @FindBy(how = How.ID, using = "buscar")
  private WebElement searchButton;

  private static final String PICK_UP_LOCATION_ITEM_PATH =
      "//div[@class='autocomplete-suggestions' and not(contains(@style,'display: none'))]//span[contains(text(),'%s')]";
  private static final String DATE_FIELD_PATH = ".open [aria-label='%s']";
  private static final String MONTH_ITEM_PATH =
      "//option[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]";

  public CarSearchComponents(WebDriver driver) {
    super(driver);
  }

  private WebElement getPickUpLocationItem(String name) {

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath(String.format(PICK_UP_LOCATION_ITEM_PATH, name))));
  }

  private void selectDates() {

    LocalDate pickUpDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    LocalDate dropOffDate = pickUpDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

    if (DataGenerator.getMonth(pickUpDate).equals(DataGenerator.getMonth(LocalDate.now()))
        && previousMonthPicker.isDisplayed()) click(previousMonthPicker);
    WebElement dateElement =
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(
                    String.format(DATE_FIELD_PATH, DataGenerator.applyDateFormat(pickUpDate)))));
    click(dateElement);
    click(dropOffDateInput);
    WebElement dropOffDateElement =
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(
                    String.format(DATE_FIELD_PATH, DataGenerator.applyDateFormat(dropOffDate)))));
    click(dropOffDateElement);
  }

  public void searhCar(SearchCriteriaInfo searchCriteriaInfo) {
    click(pickupLocationInput);
    sendKeys(pickupLocationInput, searchCriteriaInfo.getEntireLocation());
    click(getPickUpLocationItem(searchCriteriaInfo.getLocation()));
    selectDates();
    click(searchButton);
  }
}
