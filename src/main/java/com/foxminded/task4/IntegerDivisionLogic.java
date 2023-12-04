package com.foxminded.task4;

import java.util.ArrayList;

public class IntegerDivisionLogic {

	private int currentDigitPositionInDividend;

	private int remain = 0;
	private int quotient = 0;
	private int newDividend = 0;
	private int nextDigitOfQuotient = 0;
	private DivisionVector quotientAndRemaining = null;
	private ArrayList<int[]> result = new ArrayList<>();

	protected DivisionVector getQuotientAndRemainingFromDivision(int dividend, int divisor) {
		DivisionVector quotientAndRemaining = new DivisionVector();
		quotientAndRemaining.quotient = dividend / divisor;
		remain = dividend % divisor;

		return quotientAndRemaining;
	}

	protected int getDigitByPosition(int number, int digitPosition) {
		currentDigitPositionInDividend = NumbersOperations.countOfDigitsInNumber(number);
		int i = 0;
		while (number >= Math.pow(10, digitPosition + 1)) {
			number /= 10;
			i++;
		}
		currentDigitPositionInDividend -= i;

		return number % 10;
	}

	protected int getNumberPlacedBetweenZeroPositionAndDigitPosition(int number, int digitPosition) {
		currentDigitPositionInDividend = NumbersOperations.countOfDigitsInNumber(number);
		int i = 0;
		while (number >= Math.pow(10, digitPosition + 1)) {
			number /= 10;
			i++;
		}
		currentDigitPositionInDividend -= i;

		return number;
	}

	protected int getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(int dividend, int divisor) {
		int num = getNumberPlacedBetweenZeroPositionAndDigitPosition(dividend, 0);

		int i = 0;
		while (num != dividend && num / divisor <= 0) {
			i++;
			num = getNumberPlacedBetweenZeroPositionAndDigitPosition(dividend, i);
		}
		return num;
	}

	protected boolean isDivisionAllowed(int dividend) {
		return currentDigitPositionInDividend != NumbersOperations.countOfDigitsInNumber(dividend);
	}

	protected void calculateNewDividendAndQuotient(int dividend, int divisor) {
		quotient = NumbersOperations.concatenateTwoNumbersToOne(quotient, nextDigitOfQuotient);
		newDividend = NumbersOperations.concatenateTwoNumbersToOne(remain, getDigitByPosition(dividend, currentDigitPositionInDividend));

		while (newDividend < divisor && isDivisionAllowed(dividend)) {
			newDividend = NumbersOperations.concatenateTwoNumbersToOne(newDividend, getDigitByPosition(dividend, currentDigitPositionInDividend));

			quotient = NumbersOperations.concatenateTwoNumbersToOne(quotient, 0);
		}
	}

	protected DivisionVector calculateDivisionOffset(int dividend, int divisor) {
		DivisionVector divisionVectorOffsets = new DivisionVector();

		while (isDivisionAllowed(dividend)) {
			quotientAndRemaining = getQuotientAndRemainingFromDivision(newDividend, divisor);

			nextDigitOfQuotient = quotientAndRemaining.quotient;

			divisionVectorOffsets.divisionResultOffset = currentDigitPositionInDividend - NumbersOperations.countOfDigitsInNumber(divisor * nextDigitOfQuotient);

			calculateNewDividendAndQuotient(dividend, divisor);

			divisionVectorOffsets.subtractionResultOffset = currentDigitPositionInDividend - NumbersOperations.countOfDigitsInNumber(newDividend);

			result.add(new int[] { divisor * nextDigitOfQuotient, newDividend, divisionVectorOffsets.divisionResultOffset, divisionVectorOffsets.subtractionResultOffset });
		}

		return divisionVectorOffsets;
	}

	protected int calculateSubtractionOffsettBasedOnRemain(int dividend, int divisor, int lastNumBeforeRemainder, DivisionVector divisionVectorOffsets) {
		if (remain == 0) {
			if (dividend / divisor == 1 && dividend % divisor == 0) {
				return NumbersOperations.countOfDigitsInNumber(dividend) - 1;
			} else {
				return divisionVectorOffsets.divisionResultOffset + NumbersOperations.countOfDigitsInNumber(lastNumBeforeRemainder) - 1;
			}
		} else {
			return currentDigitPositionInDividend - NumbersOperations.countOfDigitsInNumber(remain);
		}
	}

	protected ArrayList<int[]> solvingTheProblemAndConvertingItToArray(int dividend, int divisor) {
		newDividend = getPieceOfDividendWhichIsBiggerThenDivisorStartingFromZeroPosition(dividend, divisor);

		DivisionVector divisionVectorFirstLine = new DivisionVector();
		divisionVectorFirstLine.curDividend = newDividend;

		DivisionVector divisionVectorOffsets = calculateDivisionOffset(dividend, divisor);

		quotientAndRemaining = getQuotientAndRemainingFromDivision(newDividend, divisor);

		int lastNumBeforeRemainder = nextDigitOfQuotient * divisor;

		nextDigitOfQuotient = quotientAndRemaining.quotient;
		quotient = NumbersOperations.concatenateTwoNumbersToOne(quotient, quotientAndRemaining.quotient);

		if (nextDigitOfQuotient != 0) {
			divisionVectorOffsets.divisionResultOffset = currentDigitPositionInDividend - NumbersOperations.countOfDigitsInNumber(divisor * nextDigitOfQuotient);
			lastNumBeforeRemainder = divisor * nextDigitOfQuotient;
		}

		divisionVectorOffsets.subtractionResultOffset = calculateSubtractionOffsettBasedOnRemain(dividend, divisor, lastNumBeforeRemainder, divisionVectorOffsets);

		divisionVectorFirstLine.quotient = quotient;

		result.add(0, new int[] { divisionVectorFirstLine.curDividend, divisionVectorFirstLine.quotient });

		if (nextDigitOfQuotient == 0) {
			int[] tmp_arr = result.get(result.size() - 1);
			tmp_arr[tmp_arr.length - 1] = divisionVectorOffsets.subtractionResultOffset;
			result.remove(result.size() - 1);
			result.add(tmp_arr);
		} else {
			result.add(new int[] { divisor * nextDigitOfQuotient, remain, divisionVectorOffsets.divisionResultOffset,
					divisionVectorOffsets.subtractionResultOffset });
		}

		return result;
	}
}
