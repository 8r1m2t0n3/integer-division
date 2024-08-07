package com.brimstone.integer_division;

import java.util.ArrayList;

public class Renderer {

  private int currentLine = 0;
  private final StringBuilder output = new StringBuilder();

  protected int[] getQuotientAndRemoveItFromDivisionResult(ArrayList<int[]> divisionResult) {
    return divisionResult.remove(0);
  }

  protected void printFirstLine(Integer dividend, Integer divisor) {
    currentLine++;
    output.append("_")
        .append(dividend.toString())
        .append("|")
        .append(divisor.toString())
        .append('\n');
  }

  protected void printSpecifiedOffset(int offset) {
    for (int i = 0; i < offset; i++) {
      output.append(" ");
    }
  }

  protected void printSecondLine(Integer offset, Integer dividend, Integer divisor,
      Integer curDividend, Integer quotient) {
    currentLine++;

    printSpecifiedOffset(offset + 1);

    output.append(curDividend.toString());

    printSpecifiedOffset(NumbersOperations.countOfDigitsInNumber(dividend) - (
        NumbersOperations.countOfDigitsInNumber(curDividend) + offset));

    output.append("|");

    for (int j = 0; j < Math.max(NumbersOperations.countOfDigitsInNumber(divisor),
        NumbersOperations.countOfDigitsInNumber(quotient)); j++) {
      output.append("-");
    }

    output.append('\n');
  }

  protected void printThirdLine(Integer offset, Integer prevNum, Integer num, Integer quotient,
      Integer dividend) {
    currentLine++;
    for (int i = 0; i < offset + 1; i++) {
      output.append(" ");
    }

    for (int i = 0; i < NumbersOperations.countOfDigitsInNumber(num); i++) {
      output.append("-");
    }

    printSpecifiedOffset(
        NumbersOperations.countOfDigitsInNumber(dividend) - NumbersOperations.countOfDigitsInNumber(
            prevNum));

    output.append("|").append(quotient).append('\n');
  }

  protected void printForthLine(Integer offset, Integer curRemain, Integer linesCount) {
    currentLine++;
    printSpecifiedOffset(offset);

    if (currentLine != linesCount) {
      output.append("_").append(curRemain).append('\n');
    } else {
      output.append(" ").append(curRemain.toString()).append('\n');
    }
  }

  protected void printNextThreeLines(Integer divisionResultOffset, Integer subtractionResultOffset,
      Integer curDividend, Integer curRemain, Integer linesCount) {
    currentLine++;
    printSpecifiedOffset(divisionResultOffset + 1);
    output.append(curDividend.toString()).append('\n');

    currentLine++;
    printSpecifiedOffset(divisionResultOffset + 1);
    for (int i = 0; i < NumbersOperations.countOfDigitsInNumber(curDividend); i++) {
      output.append("-");
    }
    output.append('\n');

    currentLine++;
    printSpecifiedOffset(subtractionResultOffset);
    if (currentLine != linesCount) {
      output.append("_").append(curRemain).append('\n');
    } else {
      output.append(" ").append(curRemain.toString()).append('\n');
    }
  }

  protected int getLinesCount(ArrayList<int[]> divisionResult) {
    int linesPerIteration = 3;
    return (divisionResult.size() - 1) * linesPerIteration + 1;
  }

  public String convertDivisionResultToString(Integer dividend, Integer divisor,
      ArrayList<int[]> divisionResult) {
    int linesCount = getLinesCount(divisionResult);

    int[] firstDividendAndQuotient = getQuotientAndRemoveItFromDivisionResult(divisionResult);
    int firstDividend = firstDividendAndQuotient[0];
    int quotient = firstDividendAndQuotient[1];

    printFirstLine(dividend, divisor);

    for (int[] n : divisionResult) {

      DivisionVector result = new DivisionVector(n);

      if (currentLine == 1) {
        printSecondLine(result.divisionResultOffset, dividend, divisor, result.curDividend,
            quotient);
        printThirdLine(result.divisionResultOffset, firstDividend, result.curDividend, quotient,
            dividend);
        printForthLine(result.subtractionResultOffset, result.curRemain, linesCount);
      } else {
        printNextThreeLines(result.divisionResultOffset, result.subtractionResultOffset,
            result.curDividend, result.curRemain, linesCount);
      }

      firstDividend = result.curRemain;
    }

    return String.valueOf(output);
  }
}
