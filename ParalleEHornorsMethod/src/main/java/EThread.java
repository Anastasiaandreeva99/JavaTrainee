import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.BrokenBarrierException;

public class EThread extends Thread {

	private Integer intervalStart;
	private Integer step;
	private BigDecimal accumulator = BigDecimal.ZERO;

	public EThread(Integer start, Integer step) {
		this.intervalStart = start;
		this.step = step;
	}

	public void run() {
		System.out.println("starting at" + intervalStart);
		for (int i = intervalStart; i > 0; i = i - step) {
			BigDecimal divisor = new BigDecimal(i * (i + 1));
			accumulator = accumulator.divide(divisor, 10000, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		}

		try {
			Calculator.barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {}
	}

	public BigDecimal getAccumulator() {
		return accumulator;
	}
	
}
