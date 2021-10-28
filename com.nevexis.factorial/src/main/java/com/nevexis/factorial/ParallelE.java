package com.nevexis.factorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParallelE {

	private static class ETask extends RecursiveTask<Long> {
		protected List<String> text;
		int i;

		private ETask(List<String> text, int i) {

			this.i = i;
			this.text= text;
		}

		@Override
		protected Long compute() {
			if (i >= text.size())
				return (long) 0;
			ETask ft = new ETask(text, i + 1);
			ft.fork();
			if (text.get(i).equals("google")) {
				return 1 + ft.join();
			}
			else
			{
				return ft.join();
			}
		}
	}

	public long words(String text) {
		List<String> words = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(text);
		
		while (matcher.find()) {
			words.add(matcher.group(0));
		}
        
		ForkJoinTask<Long> fjt = new ETask(words,0);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(fjt);
	}

	public static void main(String[] args) {
		long result = new ParallelE().words("google hello.Come on!Hello google,how are you.Come on google. google");
		System.out.println(result);
	}
}
