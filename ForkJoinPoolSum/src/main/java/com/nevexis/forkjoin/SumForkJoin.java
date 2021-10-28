package com.nevexis.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.stream.StreamSupport;

public class SumForkJoin extends RecursiveTask<Integer> {

	private static final int LIMIT = 10;

	private static final int TRESHOLD = 5;

	private Spliterator<Integer> numbers;

	public SumForkJoin(Spliterator<Integer> numbers) {
		this.numbers = numbers;

	}

	@Override
	protected Integer compute() {
		if (numbers.estimateSize() <= TRESHOLD) {
			return StreamSupport.stream(numbers, true).reduce(0, (total, el) -> total + el);
			
		}
			Spliterator<Integer> right = numbers.trySplit();
	        try {
	            return new SumForkJoin(numbers).fork().get() + new SumForkJoin(right).fork().get();
	        
		} catch (InterruptedException | ExecutionException e) {
			
		}
		return 0;
	}

	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println(new SumForkJoin(ints.stream().spliterator()).invoke());

	}
}