package com.revature.util;

public class ValidateCC {
  private static int VISA = 4;
  private static int MASTERCARD = 5;
  private static int AMERICAN_EXPRESS = 37;
  private static int DISCOVER = 6;

  public static boolean validateCard(String cardNumber) {
    return (cardNumber.length() >= 13 &&
            cardNumber.length() <= 16) &&
           (checkPrefix(cardNumber, VISA) ||
            checkPrefix(cardNumber, MASTERCARD) ||
            checkPrefix(cardNumber, AMERICAN_EXPRESS) ||
            checkPrefix(cardNumber, DISCOVER)) ||
           (doubleEverySecondDigitSum(cardNumber) +
            digitOddSum(cardNumber) % 10 == 0);
  }

  private static int getPrefix(String cardNumber, int prefixLength) {
    return Integer.parseInt(cardNumber.substring(0, prefixLength));
  }

  private static boolean checkPrefix(String cardNumber, int prefix) {
    return getPrefix(cardNumber, prefix) == prefix;
  }

  private static int doubleEverySecondDigitSum(String cardNumber) {
    int sum = 0;

    for (int i = cardNumber.length() - 2; i >= 0; i -= 2) {
      sum += processDigit(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) * 2);
    }

    return sum;
  }

  private static int digitOddSum(String cardNumber) {
    int sum = 0;

    for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
      sum += Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
    }

    return sum;
  }

  private static int processDigit(int digit) {
    if (digit < 9) {
      return digit;
    }

    return (digit / 10) + (digit % 10);
  }
}
