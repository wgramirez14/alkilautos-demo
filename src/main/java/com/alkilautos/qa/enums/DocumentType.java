package com.alkilautos.qa.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum DocumentType {
  CC("Cédula de Ciudadanía"),
  CE("Cédula de Extranjeria"),
  NIT("Número de identificación tributaria"),
  PA("Pasaporte");

  private final String documentTypeName;

  DocumentType(String documentTypeName) {
    this.documentTypeName = documentTypeName;
  }

  public static DocumentType fromName(String name) {
    return Arrays.stream(DocumentType.values())
        .filter(v -> v.name().equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown document type: " + name));
  }
}
