package com.brimstone.integer_division;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RendererTest extends Renderer {

	@Test
	void convertDivisionResultToString_shouldReturnCorrectMainsolvingTheProblemAndConvertingItToArray() {
		int dividend = 78945;
		int divisor = 4;
		
		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 7, 19736 }, 
				new int[] { 4, 38, 0, 0 },
				new int[] { 36, 29, 0, 1 },
				new int[] { 28, 14, 1, 2 },
				new int[] { 12, 25, 2, 3 }, 
				new int[] { 24, 1, 3, 4 }));
		
		Assertions.assertEquals(
				  "_78945|4\n"
				+ " 4    |-----\n" /* offset1 = 0 */
				+ " -    |19736\n"
				+ "_38\n"          /* offset2 = 0 */
				+ " 36\n"          /* offset1 = 0 */
				+ " --\n"
				+ " _29\n"         /* offset2 = 1 */
				+ "  28\n"         /* offset1 = 1 */
				+ "  --\n"
				+ "  _14\n"        /* offset2 = 2 */
				+ "   12\n"        /* offset1 = 2 */
				+ "   --\n"
				+ "   _25\n"       /* offset2 = 3 */
				+ "    24\n"       /* offset1 = 3 */
				+ "    --\n"
				+ "     1\n",      /* offset2 = 4 */    
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_allNumbersShouldBePlacedWithOffset() {
		int dividend = 100000;
		int divisor = 90;
		
		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 100, 1111 }, 
				new int[] { 90, 100, 1, 1 },
				new int[] { 90, 100, 2, 2 },
				new int[] { 90, 100, 3, 3 },
				new int[] { 90, 10, 4, 4 }));
		
		Assertions.assertEquals(
				  "_100000|90\n"
				+ "  90   |----\n" /* offset1 = 1 */
				+ "  --   |1111\n"
				+ " _100\n"        /* offset2 = 1 */
				+ "   90\n"        /* offset1 = 2 */
				+ "   --\n"
				+ "  _100\n"       /* offset2 = 2 */
				+ "    90\n"       /* offset1 = 3 */
				+ "    --\n"
				+ "   _100\n"      /* offset2 = 3 */
				+ "     90\n"      /* offset1 = 4 */
				+ "     --\n"
				+ "     10\n",     /* offset2 = 4 */
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceZeroUnderLastNumberInProblem_whenProblemContainsFourLinesAndRemainderEqualsToZero() {
		int dividend = 1000;
		int divisor = 10;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 10, 100,  }, 
				new int[] { 10, 0, 0, 1 }));
		
		Assertions.assertEquals(
				  "_1000|10\n"
				+ " 10  |---\n"
				+ " --  |100\n"
				+ "  0\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceOneUnderLastDigitOfDividend_whenProblemContainsFourLinesAndRemainderDoesNotEqualsToZero() {
		int dividend = 1001;
		int divisor = 10;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 10, 100 }, 
				new int[] { 10, 1, 0, 3 }));
		
		Assertions.assertEquals(
				  "_1001|10\n"
				+ " 10  |---\n"
				+ " --  |100\n"
				+ "    1\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceZeroUnderLastNumberInProblem_whenProblemContainsMoreThenFourLinesAndRemainderEqualsToZero() {
		int dividend = 10000;
		int divisor = 4;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 10, 2500 }, 
				new int[] { 8, 20, 1, 1 },
				new int[] { 20, 0, 1, 2 }));
		
		Assertions.assertEquals(
				  "_10000|4\n"
				+ "  8   |----\n"
				+ "  -   |2500\n"
				+ " _20\n"
				+ "  20\n"
				+ "  --\n"
				+ "   0\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceLastRemainderUnderLastDigitOfDividendNumber_whenProblemContainsMoreThenFourLinesAndRemainderDoesNotEqualsToZero() {
		int dividend = 10001;
		int divisor = 4;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 10, 2500 }, 
				new int[] { 8, 20, 1, 1 },
				new int[] { 20, 1, 1, 4 }));
		
		Assertions.assertEquals(
				  "_10001|4\n"
				+ "  8   |----\n"
				+ "  -   |2500\n"
				+ " _20\n"
				+ "  20\n"
				+ "  --\n"
				+ "     1\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldReturnCorrectSolution_whenProblemIsToDivideEqualNumbers() {
		int dividend = 245;
		int divisor = 245;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 245, 1 }, 
				new int[] { 245, 0, 0, 2 }));
		
		Assertions.assertEquals(
				  "_245|245\n"
				+ " 245|---\n"
				+ " ---|1\n"
				+ "   0\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceZeroUnderLastNumberInProblem_whenSolutionOfProblemContainsMoreThenFourLinesAndReminderIsZero() {
		int dividend = 2435;
		int divisor = 5;

		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 24, 487 }, 
				new int[] { 20, 43, 0, 1 },
				new int[] { 40, 35, 1, 2 },
				new int[] { 35, 0, 2, 3 }));
		
		Assertions.assertEquals(
				  "_2435|5\n"
				+ " 20  |---\n"
				+ " --  |487\n"
				+ " _43\n"
				+ "  40\n"
				+ "  --\n"
				+ "  _35\n"
				+ "   35\n"
				+ "   --\n"
				+ "    0\n", 
				convertDivisionResultToString(dividend, divisor, divisionResult));
	}
	
	@Test
	void convertDivisionResultToString_shouldPlaceZeroUnderLastNumberInProblem_whenLastLinesHasSameOffset() {
		int dividend = 2434;
		int divisor = 2;
		
		ArrayList<int[]> divisionResult = new ArrayList<>(Arrays.asList(
				new int[] { 2, 1217 }, 
				new int[] { 2, 4, 0, 1 },
				new int[] { 4, 3, 1, 2 },
				new int[] { 2, 14, 2, 2 },
				new int[] { 14, 0, 2, 3 }));
		
		Assertions.assertEquals(
				  "_2434|2\n"
				+ " 2   |----\n"
				+ " -   |1217\n"
				+ " _4\n"
				+ "  4\n"
				+ "  -\n"
				+ "  _3\n"
				+ "   2\n"
				+ "   -\n"
				+ "  _14\n"
				+ "   14\n"
				+ "   --\n"
				+ "    0\n", 
						convertDivisionResultToString(dividend, divisor, divisionResult));
	}
}
