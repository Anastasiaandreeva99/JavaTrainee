import org.apfloat.Apfloat;

public class ThreadCoordinator {
	public static void main(String[] args) {
	Config parser = new Config(args);		
	
	long startTime = System.currentTimeMillis();
	int threadCount = 20;
	int precision = 300;
	
	Apfloat sum = new Apfloat(0, precision);
	Apfloat[] results = new Apfloat[threadCount];
	Thread threads[] = new Thread[threadCount];
	int inervalSize = precision / threadCount;
	FactorialCalc fact =new FactorialCalc();
	for (int i = 0; i < threadCount; i++) {
		int from = i * inervalSize;
		int to = (i + 1) * inervalSize;
//		while(currentFactCalculated < from * 2) {
//			fact = fact.multiply(new Apfloat(currentFactCalculated));
//			currentFactCalculated++;
//		}
		MyThread r = new MyThread(from, to, results, i, parser.getPrecision(), parser.getIsQuiet(),fact);
		Thread t = new Thread(r);
		threads[i] = t;
		t.start();
	}

	for (int i = 0; i < threadCount; i++) {
		try {
			threads[i].join();
			sum = sum.add(results[i]);
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
