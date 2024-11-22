// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int countIterations = 0;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]) * 0.01;
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + (rate*100) + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + countIterations);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + countIterations);
	}

	// Computes the ending balance of a loan.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		for (int i = 0; i < n; i++) {
			balance = (balance - payment) * (1 + rate);
		}
		return balance;
	}
	
    // Uses brute force to compute the periodical payment.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		countIterations = 0;
		double payment = loan / n;
		double balance = endBalance(loan, rate, n, payment);
	
		while (balance > 0) {
			payment += epsilon;
			balance = endBalance(loan, rate, n, payment);
			countIterations++;
		}
	
		return payment - epsilon;
	}
	
    // Uses bisection search to compute the periodical payment.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        countIterations = 0;
        double lowBound = loan / n;
        double highBound = loan * Math.pow(1 + rate, n) / n;
        double midFigure;

        while (highBound - lowBound > epsilon) {
            countIterations++;
            midFigure = (lowBound + highBound) / 2;
            double balance = endBalance(loan, rate, n, midFigure);
            if (balance > 0) {
                lowBound = midFigure;
            } else {
                highBound = midFigure;
            }
        }
        return (lowBound + highBound) / 2;
    }
}