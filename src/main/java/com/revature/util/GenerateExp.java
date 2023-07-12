package com.revature.util;

import java.time.LocalDate;

public class GenerateExp {
  public static String getExpirationDate() {
    StringBuilder exp = new StringBuilder();

    return exp.append(getMonth() + "/" + getYearPlusFive()).toString();
  }

  private static String getMonth() {
    int month = LocalDate.now().getMonth().getValue();

    if (month < 10) {
      StringBuilder addZero = new StringBuilder();

      addZero.append("0" + month);

      return addZero.toString();
    }

    return String.valueOf(month);
  }

  private static String getYearPlusFive() {
    StringBuilder year = new StringBuilder();

    year.append(LocalDate.now().plusYears(5l).getYear());

    return year.substring(2, year.length());
  }
}
