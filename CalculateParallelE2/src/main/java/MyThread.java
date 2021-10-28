import org.apfloat.Apfloat;

public class MyThread implements Runnable{
	private int intervalFrom;
	private int intervalTo;
	private int step;
	private Apfloat[] results;
	private int currResult;
	private int precision;
	private Boolean isQuiet;
	private Apfloat currFactorial;


	public MyThread(int intervalFrom,Apfloat currFactorial, int intervalTo, Apfloat[] results, int currResult, int precision,
			Boolean isQuiet) {
		this.intervalFrom = intervalFrom;
		this.intervalTo = intervalTo;
		this.results = results;
		this.currResult = currResult;
		this.precision = precision;
		this.isQuiet = isQuiet;
		this.currFactorial = currFactorial;
	
	}

	@Override
	public void run() {
		
		results[currResult] = new Apfloat(0,precision);
		for(int i = intervalFrom; i < intervalTo; i++) {
			results[currResult] = results[currResult].add(new Apfloat(2 * i + 1, precision).divide(currFactorial));
			currFactorial = currFactorial.multiply(new Apfloat(intervalFrom + i * 2 + 1)).multiply(new Apfloat(intervalFrom + i * 2 + 2));
		}
		
	}
	
	

}
