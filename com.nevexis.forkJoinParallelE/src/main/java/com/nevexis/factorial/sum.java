package com.nevexis.factorial;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class sum {
	
		private static class SumTask extends RecursiveTask<Long>{
	        long n;
	        private SumTask(long n){
	            this.n = n;
	        }

	        @Override
	        protected Long compute() {
	            if(n<=1) return 1L;
	            SumTask ft = new SumTask(n-1);
	            ft.fork();
	            return n + ft.join();
	        }
	    }

	    public long factorial(long n){
	        ForkJoinTask<Long> fjt = new SumTask(n);
	        ForkJoinPool pool = new ForkJoinPool();
	        return pool.invoke(fjt);
	    }

	    public static void main(String[] args) {
	        long result = new sum().factorial(5);
	        System.out.println(result);
	    }
	}

