import java.math.BigInteger;

import org.apfloat.Apfloat;

public class ParallelEThread extends Thread{
	
	private int intervalFrom;
	private int intervalTo;
//	private int step;
	private BigInteger results;
//	private int currResult;
//	private int precision;
//	private Boolean isQuiet;
	private FactorialCalc factoriaCalc;


	public ParallelEThread(int intervalFrom, int intervalTo, FactorialCalc factoriaCalc
			) {
		this.intervalFrom = intervalFrom;
		this.intervalTo = intervalTo;
		this.results =BigInteger.valueOf(0);
//		this.currResult = currResult;
//		this.precision = precision;
//		this.isQuiet = isQuiet;
		this.factoriaCalc = factoriaCalc;
	
	}

	@Override
	public void run() {
		
		for(int i = intervalFrom; i < intervalTo; i++) {
			results= results.add(BigInteger.valueOf(1).divide(factoriaCalc.getFact(i)));
		}
		
	}
	
	public BigInteger getResult()
	{
		return results;
	}
	

}
