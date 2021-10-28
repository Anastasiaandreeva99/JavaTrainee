

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class _Calculator
{
	/**
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * NA MOITE MALKI PRIJATELKI R & S 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * 
	 */
	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		// -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
		// if greater than 1 results are not correct
		Integer granularity=10;
		Integer numberOfThreads=10;
	      long iterations = 10000;
          long threshold = iterations/numberOfThreads;
          
		// Trusted source https://www.math.utah.edu/~pa/math/e.html
		System.out.println(
				"Trusted Euler Result = 2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695");
        
	
			System.out.println("==== Threshold = " + threshold);
			long timems = System.currentTimeMillis();
			
			//do not forget the ZERO(0) - 0! (zero factorial is not included) 
			System.out.println("        Euler Result = " + BigDecimal.ONE.add(new EulerTask(BaseRecursiveTask.MIN, iterations, threshold).invoke()));
			timems = System.currentTimeMillis() - timems;
			System.out.println("Time = " + timems);
		
	}
}