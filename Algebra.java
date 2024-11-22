// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	
	// Running tests.
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2.
	public static int plus(int x1, int x2) {
		int result = x1;
		if (x2 > 0) {
			for (int i = 0; i < x2; i++) {
				result++;
			}
		} else if (x2 < 0) {
			int x2Positive = negate(x2);
			for (int i = 0; i < x2Positive; i++) {
				result--;
			}
		}
		return result;
	}

	// Returns x1 - x2.
	public static int minus(int x1, int x2) {
		return plus(x1, negate(x2));
	}
	
	// Return -(num).
	public static int negate(int num) {
		int opposite = 0;
		if (num > 0) { // If positive, make it negative
			while (num > 0) {
				num--;
				opposite--;
			}
		} else if (num < 0) { // If negative, make it positive
			while (num < 0) {
				num++;
				opposite++;
			}
		}
		return opposite;
	}

	// Returns x1 * x2 (Assisted by 'negate').
	public static int times(int x1, int x2) {
		boolean isNegative = false;
	
		if ((x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0)) {
			isNegative = true;
		}
	
		if (x1 < 0) {
			x1 = negate(x1);
		}
		if (x2 < 0) {
			x2 = negate(x2);
		}
	
		int result = 0;
		while (x2 > 0) {
			int temp = x1;
			while (temp > 0) { 
				result++;
				temp--;
			}
			x2--;
		}
	
		if (isNegative) {
			result = negate(result);
		}
	
		return result;
	}	

	// Returns x^n (for n >= 0, Assisted by 'Times').
	public static int pow(int x, int n) {
		if (n==0) {
			return 1;
		}
		int powResult = 1;
		for (int i = 0; i < n; i++) {
			powResult = times(powResult, x);
		}
		return powResult;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		// If division by 0, return a 'strange' result.
		if (x2 == 0) {
			return -9999999;
		}
		
		// Decide if result is negative.
		boolean isNegative = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
		
		// Convert to absolutes.
		if (x1 < 0) {
			x1 = negate(x1);
		}
		if (x2 < 0) {
			x2 = negate(x2);
		}

		// Supply divResult using ++counter.
		int divResult = 0;
		while (x1 >= x2) {
			x1 = minus(x1, x2);
			divResult++;
		}
		if (isNegative) {
			divResult = negate(divResult);
		}
		return divResult;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		// If division by 0, return a 'strange' result.
		if (x2 == 0) {
			return -9999999;
		}
		// Logic: modRes = x1-int(x1/x2)*x2.
		int divInt = div(x1,x2);
		int tempMult = times(divInt, x2);
		int modRes = minus(x1, tempMult);
		return modRes;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		// If square of negative, return a 'strange' result.
		if (x < 0) {
			return -9999999;
		}
		int sqrtRes = 0;
		while (times(sqrtRes, sqrtRes) <= x) {
			sqrtRes++;
		}
		// Since there is an 'unnecessary' iteration.
		return minus(sqrtRes, 1);
	}	  	  
}