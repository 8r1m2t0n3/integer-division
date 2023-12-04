package com.foxminded.task4;

import java.util.ArrayList;

public class IntegerDivisionVisual {

	private final int COUNT_OF_LINES_WHICH_CAN_BE_PRINTED_IN_ONE_ITERATION_OF_FOREACH_LOOPE = 3;
	
	private int currentLine = 0;

	private String output = new String();

	protected int[] getQuotientAndRemoveItFromDivisionResult(ArrayList<int[]> divisionResult) {
		return divisionResult.remove(0);
	}

	protected void printFirstLine(Integer dividend, Integer divisor) {
		currentLine++;
		output += "_" + dividend.toString() + "|" + divisor.toString() + '\n';
	}

	protected void printSpecifiedOffset(int offset) {
		for (int i = 0; i < offset; i++) {
			output += " ";
		}
	}
	
	protected void printSecondLine(Integer offset, Integer dividend, Integer divisor, Integer curDividend, Integer quotient) {
		currentLine++;
		
		printSpecifiedOffset(offset + 1);

		output += curDividend.toString();

		printSpecifiedOffset(NumbersOperations.countOfDigitsInNumber(dividend) - (NumbersOperations.countOfDigitsInNumber(curDividend) + offset));

		output += "|";

		for (int j = 0; j < Math.max(NumbersOperations.countOfDigitsInNumber(divisor), NumbersOperations.countOfDigitsInNumber(quotient)); j++) {
			output += "-";
		}
		
		output += '\n';
	}

	protected void printThirdLine(Integer offset, Integer prevNum, Integer num, Integer quotient, Integer dividend) {
		currentLine++;
		for (int i = 0; i < offset + 1; i++) {
			output += " ";
		}

		for (int i = 0; i < NumbersOperations.countOfDigitsInNumber(num); i++) {
			output += "-";
		}

		printSpecifiedOffset(NumbersOperations.countOfDigitsInNumber(dividend) - NumbersOperations.countOfDigitsInNumber(prevNum));
		
		output += "|" + quotient + '\n';
	}

	protected void printForthLine(Integer offset, Integer curRemain, Integer linesCount) {
		currentLine++;
		printSpecifiedOffset(offset);
		
		if (currentLine != linesCount) {
			output += "_" + curRemain + '\n';
		} else {
			output += " " + curRemain.toString() + '\n';
		}
	}

	protected void printNextThreeLines(Integer divisionResultOffset, Integer subtractionResultOffset, Integer curDividend, Integer curRemain, Integer linesCount) {
		currentLine++;
		printSpecifiedOffset(divisionResultOffset + 1);
		output += curDividend.toString() + '\n';

		currentLine++;
		printSpecifiedOffset(divisionResultOffset + 1);
		for (int i = 0; i < NumbersOperations.countOfDigitsInNumber(curDividend); i++) {
			output += "-";
		}
		output += '\n';

		currentLine++;
		printSpecifiedOffset(subtractionResultOffset);
		if (currentLine != linesCount) {
			output += "_" + curRemain + '\n';
		} else {
			output += " " + curRemain.toString() + '\n';
		}
	}

	protected int getLinesCount(ArrayList<int[]> divisionResult) {
		return (divisionResult.size() - 1) * COUNT_OF_LINES_WHICH_CAN_BE_PRINTED_IN_ONE_ITERATION_OF_FOREACH_LOOPE + 1;
	}

	public String convertDivisionResultToString(Integer dividend, Integer divisor, ArrayList<int[]> divisionResult) {
		int linesCount = getLinesCount(divisionResult);

		int[] firstDividendAndQuotient = getQuotientAndRemoveItFromDivisionResult(divisionResult);
		Integer firstDividend = firstDividendAndQuotient[0];
		Integer quotient = firstDividendAndQuotient[1];

		printFirstLine(dividend, divisor);

		for (int[] n : divisionResult) {
			
			DivisionVector result = new DivisionVector(n);

			if (currentLine == 1) {
				printSecondLine(result.divisionResultOffset, dividend, divisor, result.curDividend, quotient);
				printThirdLine(result.divisionResultOffset, firstDividend, result.curDividend, quotient, dividend);
				printForthLine(result.subtractionResultOffset, result.curRemain, linesCount);
			} else {
				printNextThreeLines(result.divisionResultOffset, result.subtractionResultOffset, result.curDividend, result.curRemain, linesCount);
			}

			firstDividend = result.curRemain;
		}
		
		String divisionVisualization = output;
		output = "";
		return divisionVisualization;
	}
}
