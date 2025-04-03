package com.brimstone.integer_division;

import java.util.ArrayList;

public class Renderer {

  private int currentLine = 0;
  private final StringBuilder output = new StringBuilder();

  public String divisionResultToString(Integer dividend, Integer divisor,
                                       ArrayList<int[]> divisionResult) {
    int linesCount = getLinesCount(divisionResult);

    int[] firstDividendAndQuotient = removeQuotientFromDivisionResult(divisionResult);
    int firstDividend = firstDividendAndQuotient[0];
    int quotient = firstDividendAndQuotient[1];

    printFirstLine(dividend, divisor);

    for (int[] n : divisionResult) {

      DivisionVector result = new DivisionVector(n);

      if (currentLine == 1) {
        printSecondLine(result.divisionResultOffset, dividend, divisor, result.currentDividend,
            quotient);
        printThirdLine(result.divisionResultOffset, firstDividend, result.currentDividend, quotient,
            dividend);
        printForthLine(result.subtractionResultOffset, result.currentRemainder, linesCount);
      } else {
        printNextThreeLines(result.divisionResultOffset, result.subtractionResultOffset,
            result.currentDividend, result.currentRemainder, linesCount);
      }

      firstDividend = result.currentRemainder;
    }

    return String.valueOf(output);
  }

  private int[] removeQuotientFromDivisionResult(ArrayList<int[]> divisionResult) {
    return divisionResult.remove(0);
  }

  private void printOffset(int offset) {
    for (int i = 0; i < offset; i++) {
      output.append(" ");
    }
  }

  private void printFirstLine(Integer dividend, Integer divisor) {
    currentLine++;
    output.append("_")
        .append(dividend.toString())
        .append("|")
        .append(divisor.toString())
        .append('\n');
  }

  private void printSecondLine(Integer offset, Integer dividend, Integer divisor,
                               Integer curDividend, Integer quotient) {
    currentLine++;

    printOffset(offset + 1);

    output.append(curDividend.toString());

    printOffset(NumberUtil.countDigits(dividend) - (
        NumberUtil.countDigits(curDividend) + offset));

    output.append("|");

    for (int j = 0; j < Math.max(NumberUtil.countDigits(divisor),
        NumberUtil.countDigits(quotient)); j++) {
      output.append("-");
    }

    output.append('\n');
  }

  private void printThirdLine(Integer offset, Integer prevNum, Integer num, Integer quotient,
                                Integer dividend) {
    currentLine++;
    for (int i = 0; i < offset + 1; i++) {
      output.append(" ");
    }

    for (int i = 0; i < NumberUtil.countDigits(num); i++) {
      output.append("-");
    }

    printOffset(
        NumberUtil.countDigits(dividend) - NumberUtil.countDigits(
            prevNum));

    output.append("|").append(quotient).append('\n');
  }

  private void printForthLine(Integer offset, Integer curRemain, Integer linesCount) {
    currentLine++;
    printOffset(offset);

    if (currentLine != linesCount) {
      output.append("_").append(curRemain).append('\n');
    } else {
      output.append(" ").append(curRemain.toString()).append('\n');
    }
  }

  private void printNextThreeLines(Integer divisionResultOffset, Integer subtractionResultOffset,
                                     Integer curDividend, Integer curRemain, Integer linesCount) {
    currentLine++;
    printOffset(divisionResultOffset + 1);
    output.append(curDividend.toString()).append('\n');

    currentLine++;
    printOffset(divisionResultOffset + 1);
    for (int i = 0; i < NumberUtil.countDigits(curDividend); i++) {
      output.append("-");
    }
    output.append('\n');

    currentLine++;
    printOffset(subtractionResultOffset);
    if (currentLine != linesCount) {
      output.append("_").append(curRemain).append('\n');
    } else {
      output.append(" ").append(curRemain.toString()).append('\n');
    }
  }

  private int getLinesCount(ArrayList<int[]> divisionResult) {
    int linesPerIteration = 3;
    return (divisionResult.size() - 1) * linesPerIteration + 1;
  }
}
