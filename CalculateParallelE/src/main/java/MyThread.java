import org.apfloat.Apfloat;

public class MyThread implements Runnable{
	private int intervalFrom;
	private int intervalTo;
	private int step;
	private Apfloat[] results;
	private int currResult;
	private FactorialCalc calc;
	private int precision;
	private Boolean isQuiet;

	public MyThread(int intervalFrom, int intervalTo, Apfloat[] results, int currResult, int precision,
			Boolean isQuiet,FactorialCalc fact) {
		this.intervalFrom = intervalFrom;
		this.intervalTo = intervalTo;
		this.results = results;
		this.currResult = currResult;
		this.precision = precision;
		this.isQuiet = isQuiet;
		calc=fact;
	
	}

	@Override
	public void run() {
		
		results[currResult] = new Apfloat(0,precision);
		for(int i = intervalFrom; i < intervalTo; i++) {
			results[currResult] = results[currResult].add(new Apfloat(2 * i + 1, precision).divide(new Apfloat(calc.getFact(2 * i))));
		}
		
	}
	
	

}
