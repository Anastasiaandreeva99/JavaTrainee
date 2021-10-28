
import java.math.BigDecimal;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
abstract class BaseRecursiveTask extends RecursiveTask<BigDecimal>
{
	/* Always start recursions from here */
	public static final int MIN = 1;

	/* How long is the partial calculation without using recursion */
	protected long threshold = 2; // partial sum/production length
	
	protected long start;
	protected long end;

	public BaseRecursiveTask(long threshold, long start, long end) {
		this.threshold = threshold;
		this.start = start;
		this.end = end;
	}

	protected long getMiddle()
	{
		return (end + start) / 2;
	}

	protected boolean isStopCondition()
	{
		return end - start <= threshold;
	}

}