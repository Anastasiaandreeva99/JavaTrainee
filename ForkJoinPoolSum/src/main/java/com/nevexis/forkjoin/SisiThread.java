package com.nevexis.forkjoin;

public class SisiThread implements Runnable {
	private Integer threadNumber;

	public SisiThread(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		if (threadNumber % 2 == 0) {
			for (int currNumber = 0; currNumber <= 1000; currNumber += 2) {
				System.out.println(Thread.currentThread().getName() + " - " + currNumber);
			}
		} else {
			for (int currNumber = 1; currNumber <= 1000; currNumber += 2) {
				System.out.println(Thread.currentThread().getName() + " - " + currNumber);
			}
		}

	}

}
