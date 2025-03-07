package esercizi;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Factorial {

    public static BigInteger recursiveFactorial(int n) {
        if (n==0 || n== 1){
            return BigInteger.valueOf(1);
        }else{
            return BigInteger.valueOf(n).multiply(recursiveFactorial(n-1));
        }
    }

    public static BigInteger memoizedFactorial(long n, Map<Long, BigInteger> map){
        if (n == 0 || n ==1){
            map.put(n, BigInteger.valueOf(1));
            return BigInteger.valueOf(1);
        }
        else if (map.containsKey(n)){
            return map.get(n);
        } else{
            BigInteger factorial = BigInteger.valueOf(n).multiply(memoizedFactorial(n-1, map));
            map.put(n,factorial);
            return factorial;
        }
    }

    public static BigInteger sequentialFactorial(long n){
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public static void main(String[] args) {
        int n = 10000;
        long start1 = System.currentTimeMillis();
        System.out.println(sequentialFactorial(n));
        long end1 = System.currentTimeMillis();
        System.out.println("sequential factorial time taken: " + (end1-start1) + "ms");
        long start2 = System.currentTimeMillis();
        System.out.println(recursiveFactorial(n));
        long end2 = System.currentTimeMillis();
        System.out.println("recursive factorial time taken: " + (end2-start2) + "ms");
        Map<Long, BigInteger> mapForFactorialValues = new HashMap<>();
        long start3 = System.currentTimeMillis();
        System.out.println(memoizedFactorial(n, mapForFactorialValues));
        long end3 = System.currentTimeMillis();
        System.out.println("memoized factorial time taken: " + (end3-start3) + "ms");
    }
}