import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FactorialCalc {
	Map<BigInteger, BigInteger> calculatedFactoriels;

	public FactorialCalc() {

		calculatedFactoriels = Collections.synchronizedMap(new HashMap<BigInteger, BigInteger>());
		calculatedFactoriels.put(BigInteger.ONE, BigInteger.ONE);
		calculatedFactoriels.put(BigInteger.ZERO, BigInteger.ONE);

	}

	public BigInteger getFact(int n) {

		resize(n);
		System.out.println(calculatedFactoriels.get(n));
		synchronized (calculatedFactoriels) {
			if (calculatedFactoriels.get(n) == null) {
				calculatedFactoriels.put(BigInteger.valueOf(n), BigInteger.valueOf(n).multiply(getFact(n - 1)));
			}

			return calculatedFactoriels.get(n);
		}
	}

	private void resize(int n) {
		while (n >= calculatedFactoriels.size()) {
			synchronized (calculatedFactoriels) {
				calculatedFactoriels.put(BigInteger.valueOf(n), BigInteger.valueOf(0));
			}
		}
	}

//	private void resize(int n) {
//		while(n >= calculatedFactoriels.size()) {
//			calculatedFactoriels.add(BigInteger.valueOf(0));
//		}
//	}

}
