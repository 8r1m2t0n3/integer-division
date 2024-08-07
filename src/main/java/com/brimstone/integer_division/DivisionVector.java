package com.brimstone.integer_division;

public class DivisionVector {

  public int quotient;
  public int curDividend;
  public int curRemain;
  public int divisionResultOffset;
  public int subtractionResultOffset;

  DivisionVector() {
    quotient = -1;
    curDividend = -1;
    curRemain = -1;
    divisionResultOffset = -1;
    subtractionResultOffset = -1;
  }

  DivisionVector(int[] result) {
    curDividend = result[0];
    curRemain = result[1];
    divisionResultOffset = result[2];
    subtractionResultOffset = result[3];
  }
}
