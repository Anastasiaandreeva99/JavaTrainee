

import java.math.BigDecimal;
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
	 * 
	 */
	public static void main(String[] args)
	{
		// -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
		// if greater than 1 results are not correct
		EulerTask.setGranularity(BaseRecursiveTask.MIN);

		// Trusted source https://www.math.utah.edu/~pa/math/e.html
		System.out.println(
				"Trusted Euler Result = 2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695");

		System.out.println("Min is ms: " + LongStream.rangeClosed(20, 500).map((threshold) -> {
			// do not pass in parallel;
			System.out.println("==== Threshold = " + threshold);
			long timems = System.currentTimeMillis();
			
			//do not forget the ZERO(0) - 0! (zero factorial is not included) 
			System.out.println("        Euler Result = " + BigDecimal.ONE.add(new EulerTask(BaseRecursiveTask.MIN, 5000, threshold).invoke().setScale(135, BigDecimal.ROUND_HALF_DOWN)));
			timems = System.currentTimeMillis() - timems;
			System.out.println("Time = " + timems);
			return timems;
		}).min());
	}
}