import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiFunction;

public class BiggestSmallestInteger {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(bfr.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String command = bfr.readLine();

        BiFunction<int[], String, Integer> smallestElement = (a, b) -> {
           int smallestNumber = Integer.MAX_VALUE;

           if (b.contains("Even")){
               for (int i = 0; i < a.length; i++) {
                   if (a[i] % 2 == 0 && a[i] < smallestNumber){
                       smallestNumber = a[i];
                   }
               }
           } else {
               for (int i = 0; i < a.length; i++) {
                   if (a[i] % 2 != 0 && a[i] < smallestNumber){
                       smallestNumber = a[i];
                   }
               }
           }

           return smallestNumber;
        };

        BiFunction<int[], String, Integer> biggestElement = (a, b) -> {
            int biggestNumber = Integer.MIN_VALUE;

            if (b.contains("Even")){
                for (int i = 0; i < a.length; i++) {
                    if (a[i] % 2 == 0 && a[i] > biggestNumber){
                        biggestNumber = a[i];
                    }
                }
            } else {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] % 2 != 0 && a[i] > biggestNumber){
                        biggestNumber = a[i];
                    }
                }
            }

            return biggestNumber;
        };

        BiFunction<BiFunction<int[], String, Integer>, BiFunction<int[], String, Integer>, Integer> motherFunction = (a, b) -> {

            if (command.contains("min")){
                return a.apply(numbers, command);
            } else {
                return b.apply(numbers, command);
            }
        };

        System.out.println(motherFunction.apply(smallestElement, biggestElement));
    }
}
