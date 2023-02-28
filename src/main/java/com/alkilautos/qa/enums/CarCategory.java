package com.alkilautos.qa.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum CarCategory {
  CA("COMPACT AUTOMATIC");

  private final String carCategoryName;

  CarCategory(String carCategoryName) {
    this.carCategoryName = carCategoryName;
  }

  public static CarCategory fromName(String name) {
    return Arrays.stream(CarCategory.values())
        .filter(v -> v.carCategoryName.equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown car category: " + name));
  }
}
