package com.nevexis.forkjoin;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class PrintNumbers {
	

	private static void extracted(int p) {
		for (int i =0; i < 1000; i++) {
			if (i % 2 == p ) {
				System.out.println(Thread.currentThread().getName() + " - " + i);
			}
		}
	}

	public static void main(String[] args) {
	
		CyclicBarrier barrier = new CyclicBarrier(3);
        ForkJoinPool.commonPool().submit(()->{
        	extracted(0);
        	try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
			}
        });
        ForkJoinPool.commonPool().submit(()->{
        	extracted(1);
           	try {
    				barrier.await();
    			} catch (InterruptedException | BrokenBarrierException e) {
    			}
       });
       	try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
			}
       
        System.out.println("The end....");
	}

}
