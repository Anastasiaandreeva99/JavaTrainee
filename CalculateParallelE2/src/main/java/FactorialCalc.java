import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apfloat.Apfloat;

public class FactorialCalc {
	List<BigInteger> calculatedFactoriels;
	

	public FactorialCalc() {
		calculatedFactoriels = Collections.synchronizedList(new ArrayList<BigInteger>());
		calculatedFactoriels.add(BigInteger.ONE);
		calculatedFactoriels.add(BigInteger.ONE);

		
	}
   
	public BigInteger getFact(int n) {		
				
         resize(n);
		
		if(calculatedFactoriels.get(n) == BigInteger.valueOf(0)) {
			calculatedFactoriels.set(n, BigInteger.valueOf(n).multiply(getFact(n - 1)));
		}
			
		return calculatedFactoriels.get(n);
	}
	
	private void resize(int n) {
		while(n >= calculatedFactoriels.size()) {
			calculatedFactoriels.add(BigInteger.valueOf(0));
		}
	}

//	private void resize(int n) {
//		while(n >= calculatedFactoriels.size()) {
//			calculatedFactoriels.add(BigInteger.valueOf(0));
//		}
//	}


}
