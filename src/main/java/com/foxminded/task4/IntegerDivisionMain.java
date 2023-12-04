package com.foxminded.task4;

import java.util.ArrayList;
import java.util.Scanner;

public class IntegerDivisionMain {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter dividend: ");
		int dividend = input.nextInt();
		
		System.out.print("Enter divisor: ");
		int divisor = input.nextInt();
	
		System.out.println();	
		
		System.out.print(new IntegerDivisionVisual().convertDivisionResultToString(dividend, divisor, 
				new ArrayList<>(new IntegerDivisionLogic().solvingTheProblemAndConvertingItToArray(dividend, divisor))));
		
		input.close();
	}
}
