package com.alkilautos.qa.enums;

import lombok.Getter;

@Getter
public enum Languages {
  ENGLISH("en"),
  SPANISH("es");

  private final String languageName;

  Languages(String languageName) {
    this.languageName = languageName;
  }
}
