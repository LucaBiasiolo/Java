package esercizi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PrimeNumbers {

    // TODO: create a program that finds all prime numbers inside an array

    public static void main(String[] args) {

        List<Integer> numbers = IntStream.rangeClosed(0,1000).boxed().collect(Collectors.toList());
        for (int number : numbers) {
            System.out.println(number + " is prime? " + isPrime(number));
        }
        System.out.println("Prime numbers inside array: " + findPrimeNumbers(numbers));

        System.out.println("First 5 prime numbers: " + findFirstNPrimes(numbers, 5));
    }

    public static boolean isPrime(int number){
        if (number <2){
            return false;
        }
        else{
            for (int i = 2; i < number; i++) {
                if (number % i == 0){
                    return false;
                }
            }
            return true;
        }
    }

    public static List<Integer> findPrimeNumbers(List<Integer> integerList){
        List<Integer> primeNumbers = new ArrayList<>();
        for (int number: integerList){
            boolean isPrime = number >= 2;
            for (int i=2;i<number;i++){
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primeNumbers.add(number);
        }
        return primeNumbers;
    }

    public static List<Integer> findFirstNPrimes(List<Integer> integerList, int n){
        if (n<1){
            return new ArrayList<>();
        } else {
            return findPrimeNumbers(integerList).subList(0,n);
        }
    }
}