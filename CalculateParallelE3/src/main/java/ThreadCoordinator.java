import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apfloat.Apfloat;
//To DO:
//	-make apfloat biginteger
//	-call the function with th enew parameters
//	-opravi threadcoordinaatora
// - vij za kakvo e step,izchistii nenujnoto

public class ThreadCoordinator {
	public static void main(String[] args) {
	Config parser = new Config(args);	
	long startTime = System.currentTimeMillis();
	
	int threadCount = 1;
	int size = 200000;
	int precision = parser.getPrecision();

	FactorialCalc factCalc = new FactorialCalc();
		
	BigInteger sum = BigInteger.valueOf(0);
	ParallelEThread threads[] = new ParallelEThread[threadCount];
	
	int inervalSize = size / threadCount;
//	BigInteger fact =  BigInteger.valueOf(1);
//	int currentFactCalculated = 1;
	
	for (int i = 0; i < threadCount; i++) {
		int from = i * inervalSize;
		int to = (i + 1) * inervalSize;
//		while(currentFactCalculated < from * 2) {
//			fact = fact.multiply(BigInteger.valueOf(currentFactCalculated));
//			currentFactCalculated++;
//		}
		ParallelEThread r = new ParallelEThread(from,to, factCalc);	
		threads[i] = r;
		r.start();
	}

	for (int i = 0; i < threadCount; i++) {
		try {
			threads[i].join();
			sum = sum.add(threads[i].getResult());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	if (!parser.getIsQuiet()) {
		System.out.println(sum);
	}
	
	long endTime = System.currentTimeMillis();
	System.out.println(String.format("Running time with %s threads: %s",
			threadCount, (endTime - startTime) / 1000.0));
}
}
