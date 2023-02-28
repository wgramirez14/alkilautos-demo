package com.alkilautos.qa.dtos;

import com.alkilautos.qa.enums.CarCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchCriteriaInfo {

  private String entireLocation;
  private String location;
  private CarCategory carCategory;
}
