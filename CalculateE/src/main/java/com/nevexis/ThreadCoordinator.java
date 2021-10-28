package com.nevexis;

import java.math.BigDecimal;

import org.apfloat.Apfloat;

public class ThreadCoordinator {
	private int precision;
	private int numberOfThreads;
	private long lastElementNum = 1;
	private long intervalLastElement = Long.MAX_VALUE;
	private int intervalSize = 10 * 1000;
	private boolean hasMore = true;
	private boolean isEmpty;
	private Apfloat sum = new Apfloat(0);

	public ThreadCoordinator(int precision, int numberOfThreads) {
		super();
		this.precision = precision;
		this.numberOfThreads = numberOfThreads;
		isEmpty=false;
	}

	public void Calculate() throws InterruptedException {
		final MyThread[] threads = new MyThread[numberOfThreads];

		for (int threadNum = 0; threadNum < numberOfThreads; threadNum++) {
			System.out.println("Starting thread " + threadNum);
			long intervalStart = 0, intervalLast = 0;
			newInterval(intervalStart, intervalLast);
			threads[threadNum] = new MyThread(isEmpty, intervalStart, intervalLast, sum,precision);
			threads[threadNum].start();
		}

		System.out.println("Waiting for threads to finish");
		for (int threadId = 0; threadId < numberOfThreads; threadId++) {
			threads[threadId].join();
		}

		// (true, resultCollector.getResult().toPlainString());

	}

	public void newInterval(long intervalStart, long intervalLast) {
		if (!hasMore) {
			isEmpty=true;
			return;
		}
		final boolean canAddInterval = lastElementNum <= intervalLastElement - intervalSize;
		intervalLast = canAddInterval ? intervalLastElement + intervalSize : intervalLastElement;
		 isEmpty = lastElementNum == intervalLastElement;
		intervalStart = lastElementNum;

		hasMore = hasMore && canAddInterval;
		lastElementNum = intervalLast;

	}
}
