
import java.math.BigInteger;
import java.util.Calendar;

import org.apfloat.Apfloat;

public class MyThread extends Thread {

    String nameThread;
    int precision;
    int numThread;
    BigInteger intervalStart;
    BigInteger intervalEnd;
    boolean quiet;
    long timeOfStart;
    long timeOfEnd;
    Apfloat sum;
    BigInteger currFactoriel;
    
    public MyThread(String nameThread, int precision, int numThread, BigInteger intervalStart, BigInteger intervalEnd, boolean quiet) {
        this.nameThread = nameThread;
        this.precision = precision;
        this.numThread = numThread;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.quiet = quiet;
        sum = new Apfloat(0, precision);
    }

    public void run() {
        timeOfStart = Calendar.getInstance().getTimeInMillis();
        
       
           Apfloat denominator = new Apfloat(factorial(in), precision);
        
      
        sum = sum.add(Apfloat.ONE.divide(denominator));

        for (BigInteger t = firstTerm.add(BigInteger.ONE); t.compareTo(lastTerm) < 0; t = t.add(BigInteger.ONE)) {
            numerator = numerator.add(new Apfloat(2, precision));
            denominator = denominator.multiply(new Apfloat(t.multiply(BigInteger.valueOf(2)).multiply(t.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE)), precision));
            sum = sum.add(numerator.divide(denominator));
        }

        numerator = numerator.add(new Apfloat(2, precision));
        denominator = denominator.multiply(new Apfloat(lastTerm.multiply(BigInteger.valueOf(2)).multiply(lastTerm.multiply(BigInteger.valueOf(2).subtract(BigInteger.ONE))), precision));
        lastTermValue = numerator.divide(denominator);

        timeOfEnd = Calendar.getInstance().getTimeInMillis();

       
    }

    public static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) // n <= 1
        {
            return BigInteger.ONE;
        } else {
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }
}