package esercizi;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println("Recursive Fibonacci:");
        for (int i=0; i<10; i++){
            System.out.print(recursiveFibonacci(i) + " ");
        }
        System.out.println("\nIterative Fibonacci:");
        for (int i=0; i<10; i++){
            System.out.print(iterativeFibonacci(i) + " ");
        }

        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("\nMemoized Fibonacci:");
        for (int i = 0; i <10; i++) {
            System.out.print(memoizedFibonacci(i, map) + " ");
        }
    }

    public static int recursiveFibonacci(int n){
        if (n == 0 || n == 1){
            return n;
        } else{
            return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
        }
    }

    public static int iterativeFibonacci(int n){
        int fibonacciNMinus2 = 0;
        int fibonacciNMinus1 = 1;
        int fibonacciN = 0;

        if (n == 0 || n==1){
            return n;
        } else {
            for (int i = 1; i < n; i++) {
                fibonacciN = fibonacciNMinus1 + fibonacciNMinus2;
                fibonacciNMinus2 = fibonacciNMinus1;
                fibonacciNMinus1 = fibonacciN;
            }
        }
        return fibonacciN;
    }

    public static int memoizedFibonacci(int n, Map<Integer, Integer> map) {
        if(n == 0 || n == 1){
            return n;
        } else if (map.containsKey(n)){
            return map.get(n);
        } else{
            int fibonacciValue = memoizedFibonacci(n-1, map) + memoizedFibonacci(n-2, map);
            map.put(n, fibonacciValue);
            return fibonacciValue;
        }
    }
}
