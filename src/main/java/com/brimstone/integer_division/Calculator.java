package com.brimstone.integer_division;

import java.util.ArrayList;

public class Calculator {

  private int currentDigitPositionInDividend;
  private int remain = 0;
  private int quotient = 0;
  private int newDividend = 0;
  private int nextDigitOfQuotient = 0;
  private DivisionVector quotientAndRemaining = null;
  private final ArrayList<int[]> result = new ArrayList<>();

  public ArrayList<int[]> solve(int dividend, int divisor) {
    newDividend = getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(dividend,
        divisor);

    DivisionVector divisionVectorFirstLine = new DivisionVector();
    divisionVectorFirstLine.currentDividend = newDividend;

    DivisionVector divisionVectorOffsets = calculateDivisionOffset(dividend, divisor);

    quotientAndRemaining = getQuotientAndRemainingFromDivision(newDividend, divisor);

    int lastNumBeforeRemainder = nextDigitOfQuotient * divisor;

    nextDigitOfQuotient = quotientAndRemaining.quotient;
    quotient = NumberUtil.concatenate(quotient,
        quotientAndRemaining.quotient);

    if (nextDigitOfQuotient != 0) {
      divisionVectorOffsets.divisionResultOffset =
          currentDigitPositionInDividend - NumberUtil.countDigits(
              divisor * nextDigitOfQuotient);
      lastNumBeforeRemainder = divisor * nextDigitOfQuotient;
    }

    divisionVectorOffsets.subtractionResultOffset = calculateSubtractionOffsetBasedOnRemainder(
        dividend, divisor, lastNumBeforeRemainder, divisionVectorOffsets);

    divisionVectorFirstLine.quotient = quotient;

    result.add(0, new int[]{divisionVectorFirstLine.currentDividend, divisionVectorFirstLine.quotient});

    if (nextDigitOfQuotient == 0) {
      int[] tmp_arr = result.get(result.size() - 1);
      tmp_arr[tmp_arr.length - 1] = divisionVectorOffsets.subtractionResultOffset;
      result.remove(result.size() - 1);
      result.add(tmp_arr);
    } else {
      result.add(new int[]{divisor * nextDigitOfQuotient, remain,
          divisionVectorOffsets.divisionResultOffset,
          divisionVectorOffsets.subtractionResultOffset});
    }

    return result;
  }

  private DivisionVector getQuotientAndRemainingFromDivision(int dividend, int divisor) {
    DivisionVector quotientAndRemaining = new DivisionVector();
    quotientAndRemaining.quotient = dividend / divisor;
    remain = dividend % divisor;

    return quotientAndRemaining;
  }

  private int getDigitByPosition(int number, int digitPosition) {
    currentDigitPositionInDividend = NumberUtil.countDigits(number);
    int i = 0;
    while (number >= Math.pow(10, digitPosition + 1)) {
      number /= 10;
      i++;
    }
    currentDigitPositionInDividend -= i;

    return number % 10;
  }

  private int getNumberBetweenZeroPositionAndDigitPosition(int number, int digitPosition) {
    currentDigitPositionInDividend = NumberUtil.countDigits(number);
    int i = 0;
    while (number >= Math.pow(10, digitPosition + 1)) {
      number /= 10;
      i++;
    }
    currentDigitPositionInDividend -= i;

    return number;
  }

  private int getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(int dividend,
                                                                                 int divisor) {
    int num = getNumberBetweenZeroPositionAndDigitPosition(dividend, 0);

    int i = 0;
    while (num != dividend && num / divisor <= 0) {
      i++;
      num = getNumberBetweenZeroPositionAndDigitPosition(dividend, i);
    }
    return num;
  }

  private boolean isDivisionAllowed(int dividend) {
    return currentDigitPositionInDividend != NumberUtil.countDigits(dividend);
  }

  private void calculateNewDividendAndQuotient(int dividend, int divisor) {
    quotient = NumberUtil.concatenate(quotient, nextDigitOfQuotient);
    newDividend = NumberUtil.concatenate(remain,
        getDigitByPosition(dividend, currentDigitPositionInDividend));

    while (newDividend < divisor && isDivisionAllowed(dividend)) {
      newDividend = NumberUtil.concatenate(newDividend,
          getDigitByPosition(dividend, currentDigitPositionInDividend));

      quotient = NumberUtil.concatenate(quotient, 0);
    }
  }

  private DivisionVector calculateDivisionOffset(int dividend, int divisor) {
    DivisionVector divisionVectorOffsets = new DivisionVector();

    while (isDivisionAllowed(dividend)) {
      quotientAndRemaining = getQuotientAndRemainingFromDivision(newDividend, divisor);

      nextDigitOfQuotient = quotientAndRemaining.quotient;

      divisionVectorOffsets.divisionResultOffset =
          currentDigitPositionInDividend - NumberUtil.countDigits(
              divisor * nextDigitOfQuotient);

      calculateNewDividendAndQuotient(dividend, divisor);

      divisionVectorOffsets.subtractionResultOffset =
          currentDigitPositionInDividend - NumberUtil.countDigits(newDividend);

      result.add(new int[]{divisor * nextDigitOfQuotient, newDividend,
          divisionVectorOffsets.divisionResultOffset,
          divisionVectorOffsets.subtractionResultOffset});
    }

    return divisionVectorOffsets;
  }

  private int calculateSubtractionOffsetBasedOnRemainder(int dividend, int divisor,
                                                         int lastNumBeforeRemainder, DivisionVector divisionVectorOffsets) {
    if (remain == 0) {
      if (dividend / divisor == 1 && dividend % divisor == 0) {
        return NumberUtil.countDigits(dividend) - 1;
      } else {
        return divisionVectorOffsets.divisionResultOffset + NumberUtil.countDigits(
            lastNumBeforeRemainder) - 1;
      }
    } else {
      return currentDigitPositionInDividend - NumberUtil.countDigits(remain);
    }
  }
}
