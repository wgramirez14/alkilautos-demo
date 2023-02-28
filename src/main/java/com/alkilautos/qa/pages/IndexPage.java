package com.alkilautos.qa.pages;

import com.alkilautos.qa.components.CarResultsComponents;
import com.alkilautos.qa.components.CarSearchComponents;
import com.alkilautos.qa.components.CommonComponents;
import com.alkilautos.qa.dtos.SearchCriteriaInfo;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexPage extends BasePage {

  private static final Logger logger = LoggerFactory.getLogger(IndexPage.class);
  private final CarSearchComponents carSearchComponents;
  private final CarResultsComponents carResultsComponents;
  private final CommonComponents commonComponents;

  public IndexPage(WebDriver driver) {
    super(driver);
    this.carSearchComponents = new CarSearchComponents(driver);
    this.carResultsComponents = new CarResultsComponents(driver);
    this.commonComponents = new CommonComponents(driver);
  }

  public void searchCar(SearchCriteriaInfo searchCriteriaInfo) {

    commonComponents.acceptCookies();
    carSearchComponents.searhCar(searchCriteriaInfo);
    carResultsComponents.filterCarResults(searchCriteriaInfo.getCarCategory().getCarCategoryName());
  }

  public String getSelectedCarName() {

    return getText(carResultsComponents.getCarNameList().get(0));
  }

  public ConfirmReservationPage selectCarFromResults() {
    click(carResultsComponents.getViewDealButtons().get(0));
    ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(newTb.get(1));
    return new ConfirmReservationPage(driver);
  }
}
