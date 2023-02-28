package com.alkilautos.qa.utils;

import com.alkilautos.qa.exception.IncorrectPropertiesFileNameException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PropertiesLoader {

  private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

  public static Properties getPropertiesInfo(String propertiesFileName)
      throws IncorrectPropertiesFileNameException {

    try (InputStream stream = ClassLoader.getSystemResourceAsStream(propertiesFileName)) {

      logger.info(String.format("Getting properties file %s", propertiesFileName));
      Properties prop = new Properties();
      prop.load(stream);

      return prop;

    } catch (Exception ex) {
      logger.warn(Arrays.toString(ex.getStackTrace()));
      throw new IncorrectPropertiesFileNameException(ex.getMessage());
    }
  }
}
