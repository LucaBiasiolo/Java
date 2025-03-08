package esercizi;

import java.util.List;

public class UtilMethods {

    public static List<Integer> filterOddOrEvenNumbers(List<Integer> integerList, boolean filterOdd, boolean filterEven){
        if (filterEven) {
            integerList.removeIf(number -> number % 2 == 0);
        }
        if (filterOdd){
            integerList.removeIf(number -> number % 2 != 0);
        }
        return integerList;
    }
}
