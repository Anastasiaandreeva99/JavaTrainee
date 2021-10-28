import java.math.BigDecimal;
import java.math.BigInteger;

import org.apfloat.Apfloat;

public class MyThread extends Thread {
	 private boolean isEmpty;
	 private long intervalStart;
	 private long intervalEnd;
	 private int precision;
	
	 

	public MyThread(boolean isEmpty, long intervalStart, long intervalEnd,Apfloat sum ,int precision) {
		
		this.isEmpty = isEmpty;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
		this.precision=precision;
	}

	@Override
	public void run() //((3*k)^2+1)/(3k)!
	{
		
        if(isEmpty)return;
		final Apfloat result = calculateInterval(intervalStart, intervalEnd,precision);
		sum.add(result);

	}
	
	private Apfloat calculateInterval(long intervalStart, long intervalEnd,int precision) {
		final Apfloat result=new Apfloat(0);
		for(long k =intervalStart;k<=intervalEnd;k++)
		{
			
			Apfloat numerator = new Apfloat(BigDecimal.valueOf(k).multiply(BigDecimal.valueOf(3)).pow(2).add(BigDecimal.valueOf(1)),precision );
			Apfloat denominator = new Apfloat(factorial(BigDecimal.valueOf(k).multiply(BigDecimal.valueOf(3)).longValue()),precision);
            result.add(numerator.divide(denominator));
		}
		return result;
	}

	public BigInteger factorial(long n)
	{
		BigInteger result = BigInteger.ONE;

		for (long i = 2; i < n; i++)
		{
			result = result.multiply(BigInteger.valueOf(i));
		}

		return result;	
	}

}
