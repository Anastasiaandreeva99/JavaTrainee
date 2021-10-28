import org.apfloat.Apfloat;

public class ThreadCoordinator {
	private int precision;
	private int numberOfThreads;
	private long lastElementNum = 1;
	private long intervalLastElement ;
	private long intervalStart=0;
	private long intervalLast = 0 ;
	private long intervalSize;
	private boolean hasMore = true;
	private boolean isEmpty;
	static protected Apfloat sum = new Apfloat(0);

	public ThreadCoordinator(int precision, int numberOfThreads,long intervalLastElement ) {
		super();
		this.precision = precision;
		this.numberOfThreads = numberOfThreads;
		this.intervalLastElement = intervalLastElement;
		this.intervalSize =  (long) Math.ceil(intervalLastElement/numberOfThreads);
		isEmpty=false;
	}

	public void Calculate() throws InterruptedException {
		final MyThread[] threads = new MyThread[numberOfThreads];

		for (int threadNum = 0; threadNum < numberOfThreads; threadNum++) {
			System.out.println("Starting thread " + threadNum);
			newInterval();
			threads[threadNum] = new MyThread(isEmpty, intervalStart, intervalLast, sum,precision);
			threads[threadNum].start();
		}

		System.out.println("Waiting for threads to finish");
//		for (int threadId = 0; threadId < numberOfThreads; threadId++) {
//			
//			System.out.println("Finishing thread " + threadId);
//            threads[threadId].join();
//		}
		System.out.println(sum);

		// (true, resultCollector.getResult().toPlainString());

	}

	public void newInterval() {
		if (!hasMore) {
			isEmpty=true;
			return;
		}
		final boolean canAddInterval = lastElementNum <= intervalLastElement - intervalSize;
		intervalLast = canAddInterval ? lastElementNum + intervalSize : intervalLastElement;
		 isEmpty = lastElementNum == intervalLastElement;
		intervalStart = lastElementNum;
		hasMore = hasMore && canAddInterval;
		lastElementNum = intervalLast;

	}
}
