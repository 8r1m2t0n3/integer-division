package com.brimstone.integer_division;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest extends Calculator {

  @Test
  void getDigitByPosition_shouldReturnDigitInAssignedPosition() {
    Assertions.assertEquals(9, getDigitByPosition(78945, 2));
    Assertions.assertEquals(7, getDigitByPosition(78945, 0));
    Assertions.assertEquals(5, getDigitByPosition(78945, 10));
  }

  @Test
  void getNumberPlacedBetweenZeroPositionAndDigitPosition_shouldReturnNumberBetweenZeroPositionAndAssignedPosition() {
    Assertions.assertEquals(789, getNumberPlacedBetweenZeroPositionAndDigitPosition(78945, 2));
    Assertions.assertEquals(1, getNumberPlacedBetweenZeroPositionAndDigitPosition(1, 0));
    Assertions.assertEquals(1, getNumberPlacedBetweenZeroPositionAndDigitPosition(1, 0));
  }

  @Test
  void getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition_shouldReturnNumberStartingFromZeroPositionWhichIsBiggerOrEqualToSpecifiedNumber() {
    Assertions.assertEquals(78,
        getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(78945, 70));
    Assertions.assertEquals(789,
        getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(78945, 80));
    Assertions.assertEquals(7894,
        getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(78945, 800));
    Assertions.assertEquals(7894,
        getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(78945, 7000));
  }


  @Test
  void solvingTheProblemAndConvertingItToArray_shouldReturnCorrectMainsolvingTheProblemAndConvertingItToArray() {
    int[] problemSolutionConvertedToStringTheoretical = {7, 19736, /* firstDividend, quotient */
        4, 38, 0, 0, /* num1, num2, offset1, offset2 */
        36, 29, 0, 1,
        28, 14, 1, 2,
        12, 25, 2, 3,
        24, 1, 3, 4};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(78945, 4));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_offsetsShouldBeCalculateCorrectly_whenAllNumbersShouldBePlacedWithOffset() {
    int[] problemSolutionConvertedToStringTheoretical = {100, 1111,
        90, 100, 1, 1,
        90, 100, 2, 2,
        90, 100, 3, 3,
        90, 10, 4, 4};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(100000, 90));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenProblemContainsFourLinesAndRemainderEqualsToZero() {
    int[] problemSolutionConvertedToStringTheoretical = {10, 100,
        10, 0, 0, 1};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(1000, 10));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenProblemContainsFourLinesAndRemainderDoesNotEqualsToZero() {
    int[] problemSolutionConvertedToStringTheoretical = {10, 100,
        10, 1, 0, 3};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(1001, 10));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenProblemContainsMoreThenFourLinesAndRemainderEqualsToZero() {
    int[] problemSolutionConvertedToStringTheoretical = {10, 2500,
        8, 20, 1, 1,
        20, 0, 1, 2};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(10000, 4));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenProblemContainsMoreThenFourLinesAndRemainderDoesNotEqualsToZero() {
    int[] problemSolutionConvertedToStringTheoretical = {10, 2500,
        8, 20, 1, 1,
        20, 1, 1, 4};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(10001, 4));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenProblemIsToDivideEqualNumbers() {
    int[] problemSolutionConvertedToStringTheoretical = {245, 1,
        245, 0, 0, 2};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(245, 245));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }

  @Test
  void solvingTheProblemAndConvertingItToArray_lastOffsetShouldBeCalculateCorrectly_whenSolutionOfProblemContainsMoreThenFourLinesAndReminderIsZero() {
    int[] problemSolutionConvertedToStringTheoretical = {24, 487,
        20, 43, 0, 1,
        40, 35, 1, 2,
        35, 0, 2, 3};

    ArrayList<int[]> problemSolutionConvertedToStringActual = new ArrayList<>(
        solvingTheProblemAndConvertingItToArray(2435, 5));

    int j = 0;

    for (int[] n : problemSolutionConvertedToStringActual) {
      for (int i : n) {
        Assertions.assertEquals(problemSolutionConvertedToStringTheoretical[j], i);
        j++;
      }
    }
  }
}
