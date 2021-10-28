
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Calendar;

import org.apfloat.Apfloat;

public class E {

	static int numThread;
	static int precision;
	static boolean quiet = false;
	static MyThread[] array;
	static Apfloat sum;

	public static void main(String[] args) throws FileNotFoundException {
		long timeOfStart = Calendar.getInstance().getTimeInMillis();

		precision = new Integer(3000);

		numThread = new Integer(17);

		array = new MyThread[numThread];
		BigInteger termsOfThread = BigInteger.valueOf(precision)
				.divide(BigInteger.valueOf(numThread));


		BigInteger currentTerm = BigInteger.ZERO;
		sum = new Apfloat(0, precision);

		while (true) {
			for (int t = 0; t < numThread; t++) {
				MyThread thread = new MyThread("Thread " + Integer.toString(t), precision, numThread,
						 currentTerm,currentTerm.add(termsOfThread), quiet);
				thread.start();
				array[t] = thread;
				currentTerm = currentTerm.add(termsOfThread);
			}

			for (int i = 0; i < numThread; i++) {
				try {
					array[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sum = sum.add(array[i].sum);
			}

			if (!quiet) {
				System.out.println("sum = " + sum.toString());
			}

			Apfloat nth = array[numThread - 1].lastTermValue;
			if (sum.equals(sum.add(nth))) {
				break;
			}

		}

		// Write to file
		

		long timeOfEnd = Calendar.getInstance().getTimeInMillis();

		System.out.println("Time of calculate: " + (timeOfEnd - timeOfStart) + " ms.");
		if (!quiet) {
			System.out.println("Result: " + sum);
		}
	}

	// Evaluate n!
	public static BigInteger factorial(BigInteger n) {
		if (n.compareTo(BigInteger.ONE) <= 0) // n <= 1
			return BigInteger.ONE;
		else
			return n.multiply(factorial(n.subtract(BigInteger.ONE)));
	}
}
