package com.brimstone.integer_division;

public class NumberUtil {

  public static int countDigits(int number) {
    return String.valueOf(number).length();
  }

  public static int concatenate(int first, int second) {
    return first * 10 + second;
  }
}
