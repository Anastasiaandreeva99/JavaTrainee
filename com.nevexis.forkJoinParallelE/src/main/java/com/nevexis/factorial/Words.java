package com.nevexis.factorial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apfloat.Apfloat;

public class Words {

	private static class ETask extends RecursiveTask<BigDecimal> {
		static int max;
		static int precision;
		static int currIndexStart;
		static int currIndexLast;
		static int intervalSize;
		static BigDecimal sum;

		private ETask(int max,int precision,int currIndexStart,int currIndexLast,BigDecimal sum) {

			this.max=max;
			this.precision=precision;
			this.currIndexStart=currIndexStart;
			this.currIndexLast=currIndexLast;
		
		}

		@Override
		protected BigDecimal compute() {
			if (currIndexLast >= max )
				return calculate();
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
        
		ForkJoinTask<Long> fjt = new wordsTask(words,0);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(fjt);
	}

	public static void main(String[] args) {
		long result = new Words().words("google hello.Come on!Hello google,how are you.Come on google. google");
		System.out.println(result);
	}
}
