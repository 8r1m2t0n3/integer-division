package com.brimstone.integer_division;

public class NumbersOperations {
	protected static int countOfDigitsInNumber(int number) {
		return String.valueOf(number).length();
	}
	
	protected static int concatenateTwoNumbersToOne(int fstNumber, int sndNumber) {
		return fstNumber * 10 + sndNumber;
	}
}
