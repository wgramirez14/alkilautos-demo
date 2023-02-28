package com.alkilautos.qa.dtos;

import com.alkilautos.qa.enums.DocumentType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactInformation {

  private String nameOfTheRenter;
  private String lastName;
  private String dateBirth;
  private DocumentType documentType;
  private String documentNumber;
  private String mobilePhone;
  private String email;
  private String address;
  private String city;
}
