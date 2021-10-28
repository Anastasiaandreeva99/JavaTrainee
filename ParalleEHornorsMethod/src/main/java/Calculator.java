import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class Calculator {
	private Integer numOfThreads;
	private Integer iterations;

	static CyclicBarrier barrier;

	public Calculator(Integer numOfThreads, Integer iterations) {
		this.numOfThreads = numOfThreads;
		this.iterations = iterations;
		barrier = new CyclicBarrier(numOfThreads + 1);
	}

	public BigDecimal calculate() {

		EThread[] acumulator = new EThread[numOfThreads];
		
		for (int i = 0; i < numOfThreads; i++) {
			acumulator[i] = new EThread(iterations - i, numOfThreads);
			acumulator[i].start();
		}

		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
		}

		return Arrays.stream(acumulator).map(e-> e.getAccumulator()).reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println(new Calculator(2, 1000).calculate());

	}

}
