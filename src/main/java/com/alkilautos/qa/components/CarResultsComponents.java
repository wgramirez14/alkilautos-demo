package com.alkilautos.qa.components;

import com.alkilautos.qa.pages.BasePage;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class CarResultsComponents extends BasePage {

  @FindBy(how = How.CSS, using = ".bar[style='display: none;']")
  private WebElement loadingResultsBar;

  @FindBy(how = How.CSS, using = ".car-result.is-loading")
  private WebElement carResultsLoading;

  @FindBy(how = How.CSS, using = "[data-target='dropdown1']")
  private WebElement carCategoryDropdown;

  @FindBy(how = How.CSS, using = ".car-result-list .nombre")
  private List<WebElement> carNameList;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),' View Deal')]")
  private List<WebElement> viewDealButtons;

  private static final String CAR_CATEGORY_ITEM = "[data-text='%s']";

  public CarResultsComponents(WebDriver driver) {
    super(driver);
  }

  private WebElement getCarCategoryItem(String categoryName) {

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(String.format(CAR_CATEGORY_ITEM, categoryName))));
  }

  public void filterCarResults(String category) {

    waitToBeInvisible(loadingResultsBar);
    click(carCategoryDropdown);
    waitToBeInvisible(carResultsLoading);
    click(getCarCategoryItem(category));
  }
}
