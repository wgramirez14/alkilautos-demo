package com.alkilautos.qa.utils;

import com.alkilautos.qa.dtos.ContactInformation;
import com.alkilautos.qa.dtos.SearchCriteriaInfo;
import com.alkilautos.qa.enums.CarCategory;
import com.alkilautos.qa.enums.DocumentType;
import com.alkilautos.qa.exception.IncorrectPropertiesFileNameException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class define methods to generate data
 *
 * @author William Ramirez
 * @version 1.0
 */
public abstract class DataGenerator {

  private static final String DATA_PROPERTIES_NAME = "data.properties";
  private static final String SEARCH_PROPERTIES_NAME = "search.properties";
  private static final Logger logger = LoggerFactory.getLogger(DataGenerator.class);

  /**
   * Method to generate contact info. This info is stored as properties. In case there is no any
   * file, method returns contact with default info.
   *
   * @return ContactInformation Object which represents the contact info
   */
  public static ContactInformation generateContactInfo() {

    try {
      Properties prop = PropertiesLoader.getPropertiesInfo(DATA_PROPERTIES_NAME);
      return ContactInformation.builder()
          .nameOfTheRenter(prop.getProperty("contact.name"))
          .lastName(prop.getProperty("contact.lastName"))
          .dateBirth(prop.getProperty("date.birth"))
          .documentType(DocumentType.fromName(prop.getProperty("document.type")))
          .documentNumber(prop.getProperty("document.number"))
          .mobilePhone(prop.getProperty("mobile.phone"))
          .email(prop.getProperty("email"))
          .address(prop.getProperty("address"))
          .city(prop.getProperty("city"))
          .build();

    } catch (IncorrectPropertiesFileNameException e) {
      logger.warn(Arrays.toString(e.getStackTrace()));
      logger.info("Returning default contact info...");

      return ContactInformation.builder()
          .nameOfTheRenter("Tim")
          .lastName("Woods")
          .dateBirth("01/01/1987")
          .documentType(DocumentType.CC)
          .documentNumber("1094654432")
          .mobilePhone("3154780023")
          .email("pepito@gmail.com")
          .address("45 Street # 65-45")
          .city("Bogota")
          .build();
    }
  }

  /**
   * Method to set location and car type. This info is stored as properties. In case there is no any
   * file, method returns an object with default info.
   *
   * @return SearchCriteriaInfo Object which represents the location and mcar type
   */
  public static SearchCriteriaInfo getLocationAndCarType() {

    try {
      Properties prop = PropertiesLoader.getPropertiesInfo(SEARCH_PROPERTIES_NAME);
      return SearchCriteriaInfo.builder()
          .entireLocation(prop.getProperty("entire.pickup.location"))
          .location(prop.getProperty("short.pickup.location"))
          .carCategory(CarCategory.fromName(prop.getProperty("car.category").toUpperCase()))
          .build();
    } catch (IncorrectPropertiesFileNameException e) {
      logger.warn(Arrays.toString(e.getStackTrace()));
      logger.info("Returning default search criteria...");

      return SearchCriteriaInfo.builder()
          .entireLocation("El Dorado (BOG)")
          .location("El Dorado")
          .carCategory(CarCategory.CA)
          .build();
    }
  }

  /**
   * Method to get a month of a certain date according to the location and language.
   *
   * @param date a specific date
   * @return String the month name
   */
  public static String getMonth(LocalDate date) {

    return date.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en-US"));
  }

  /**
   * Method to apply a format for a certain date.
   *
   * @param date a specific date
   * @return String formatted date
   */
  public static String applyDateFormat(LocalDate date) {

    return date.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.forLanguageTag("en-US")));
  }
}
