package com.brimstone.integer_division;

import java.util.ArrayList;
import java.util.Scanner;

public class IntegerDivisionMain {

  private static final Renderer RENDERER = new Renderer();
  private static final Calculator CALCULATOR = new Calculator();

  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in)) {

      System.out.print("Enter dividend: ");
      int dividend = input.nextInt();

      System.out.print("Enter divisor: ");
      int divisor = input.nextInt();

      System.out.println();

      System.out.print(
          RENDERER.convertDivisionResultToString(dividend, divisor,
              new ArrayList<>(
                  CALCULATOR.solvingTheProblemAndConvertingItToArray(dividend, divisor))));

    }
  }
}
