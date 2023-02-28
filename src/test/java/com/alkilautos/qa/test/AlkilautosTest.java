package com.alkilautos.qa.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import com.alkilautos.qa.hooks.Hooks;
import com.alkilautos.qa.pages.ConfirmReservationPage;
import com.alkilautos.qa.pages.IndexPage;
import com.alkilautos.qa.pages.MyReservationPage;
import com.alkilautos.qa.utils.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class AlkilautosTest extends Hooks {

  private static final String START_TEST = "Starting test...";
  private static final String FINISH_TEST = "Test finished";
  private static final String CONGRATS_MESSAGE = "Congratulations";
  private static final String CANCELLED = "Cancelled by the user";
  private static final Logger logger = LoggerFactory.getLogger(AlkilautosTest.class);

  @Test(
      groups = {"regression"},
      description = "Reservation section test")
  public void testReservationSection() {
    logger.info(START_TEST);

    IndexPage indexPage = super.pageManager.getIndexPage();
    takeScreenshot("Index");

    indexPage.searchCar(DataGenerator.getLocationAndCarType());
    String selectedCarName = indexPage.getSelectedCarName();
    ConfirmReservationPage confirmReservationPage = indexPage.selectCarFromResults();

    assertThat(
        confirmReservationPage.reserveCar(DataGenerator.generateContactInfo()),
        containsString(CONGRATS_MESSAGE));

    MyReservationPage myReservationPage = confirmReservationPage.goToMyReserve();

    assertThat(myReservationPage.cancelReservation(), equalToIgnoringCase(CANCELLED));
    assertThat(myReservationPage.getCarName(), containsString(selectedCarName));

    logger.info(FINISH_TEST);
    takeScreenshot("Last step");
  }
}
