package com.revature.util;

import java.util.Random;

public class GenerateCC {
  private final static Random random = new Random(System.currentTimeMillis());

  /**
   * Generates a random valid credit card number.
   *
   * @param bin    The bank identification number, a set digits at the start of the credit card
   *               number, used to identify the bank that is issuing the credit card.
   * @param length The total length (i.e. including the BIN) of the credit card number.
   * @return A randomly generated, valid, credit card number.
   */
  public static String generate(String bin, int length) {
    int randomNumberLength = length - (bin.length() + 1);

    StringBuilder builder = new StringBuilder(bin);
    for (int i = 0; i < randomNumberLength; i++) {
      int digit = random.nextInt(10);
      builder.append(digit);
    }

    int checkDigit = getCheckDigit(builder.toString());
    builder.append(checkDigit);

    return builder.toString();
  }

  /**
   * Generates the check digit required to make the given credit card number
   * valid
   *
   * @param number The credit card number for which to generate the check digit.
   * @return The check digit required to make the given credit card number
   * valid.
   */
  private static int getCheckDigit(String number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
      int digit = Integer.parseInt(number.substring(i, (i + 1)));

      if ((i % 2) == 0) {
        digit = digit * 2;
        if (digit > 9) {
          digit = (digit / 10) + (digit % 10);
        }
      }
      sum += digit;
    }

    int mod = sum % 10;
    return ((mod == 0) ? 0 : 10 - mod);
  }
}
