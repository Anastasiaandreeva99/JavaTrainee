package com.nevexis.forkjoin;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLock {

	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition1 = lock.newCondition();
	private static Integer number = 1;

	public static void print() throws InterruptedException {

		try {
			lock.lock();
			while (number <= 1000) {				
				System.out.println(Thread.currentThread().getName() + "-" + number++);
				condition1.signal();
				condition1.await();
			}
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(() -> {
			try {
				print();
			} catch (InterruptedException e) {
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			try {
				print();
			} catch (InterruptedException e) {
			}
		});
		t2.start();
		t1.join();
		t2.join();



	}
}
//
//
//AtomicInteger atomic = new AtomicInteger(0);
//ForkJoinPool.commonPool().submit(() -> {
//	try {
//		print(evenCondition, oddCondition);
//		atomic.getAndIncrement();
//	} catch (InterruptedException e) {
//
//	}
//});
//ForkJoinPool.commonPool().submit(() -> {
//	try {
//		print(oddCondition, evenCondition);
//		atomic.getAndIncrement();
//	} catch (InterruptedException e) {
//
//	}
//});
//
//while (atomic.get() < 2) {
//	try {
//		Thread.sleep(10);
//	} catch (InterruptedException e) {
//
//	}
//}