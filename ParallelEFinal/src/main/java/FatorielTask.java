
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.LongStream;

@SuppressWarnings("serial")
class FactorielTask extends BaseRecursiveTask
{
	public FactorielTask(long start, long end, long threshold) {
		super(threshold, start, end);
	}

	@Override
	protected BigDecimal compute()
	{
		// stop condition
		if (isStopCondition()) {
			return LongStream.rangeClosed(start, end).mapToObj(BigDecimal::valueOf).reduce(BigDecimal.ONE, BigDecimal::multiply);
		}

		// divide and conquer
		return Arrays.asList(new FactorielTask(start, getMiddle(), threshold), new FactorielTask(getMiddle() + 1, end, threshold)).stream().map(FactorielTask::fork) // first forks
				.map(a -> {
					return a.join();
				}) // next joins - do not combine in one map method
				.reduce(BigDecimal.ONE, BigDecimal::multiply);
	} // compute
	
}
