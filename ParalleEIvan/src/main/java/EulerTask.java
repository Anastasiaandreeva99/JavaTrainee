
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.LongStream;

@SuppressWarnings("serial")
class EulerTask extends BaseRecursiveTask
{
	/* do not use different than one */
	private static long granularity = 1;

	public EulerTask(long start, long end, long threshold) {
		super(threshold, start, end);
	}

	public static void setGranularity(long granularity)
	{
		EulerTask.granularity = granularity;
	}

	@Override
	protected BigDecimal compute()
	{
		// stop condition
		if (isStopCondition()) {

			// calculate once and use in the rest of the partial sum
			BigDecimal fact = BigDecimal.ONE.divide(new FactorielTask(MIN, end, threshold).invoke(), 200, BigDecimal.ROUND_HALF_UP);

			return LongStream.rangeClosed(start, end).filter(a -> {
				return a > MIN ? 0 == a % granularity : true;
			}).mapToObj((a) -> {
				return BigDecimal.valueOf(a);
			}).reduce(BigDecimal.ZERO, (result, element) -> {
				return result.multiply(element).setScale(200, BigDecimal.ROUND_HALF_UP).add(fact);
			});
		} // if

		// divide and conquer
		return Arrays.asList(new EulerTask(start, getMiddle(), threshold), new EulerTask(getMiddle() + 1, end, threshold)).stream().map(EulerTask::fork) // first forks
				.map(a -> {
					return a.join();
				}) // next joins - do not combine in one map method
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	} // compute

}