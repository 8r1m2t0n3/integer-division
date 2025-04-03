package com.brimstone.integer_division;

public class DivisionVector {

  public int quotient;
  public int currentDividend;
  public int currentRemainder;
  public int divisionResultOffset;
  public int subtractionResultOffset;

  public DivisionVector() {
    quotient = -1;
    currentDividend = -1;
    currentRemainder = -1;
    divisionResultOffset = -1;
    subtractionResultOffset = -1;
  }

  public DivisionVector(int[] result) {
    currentDividend = result[0];
    currentRemainder = result[1];
    divisionResultOffset = result[2];
    subtractionResultOffset = result[3];
  }
}
