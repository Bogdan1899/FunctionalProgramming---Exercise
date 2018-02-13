import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiPredicate;

public class TriFunctionMain {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(bfr.readLine());
        String[] names = bfr.readLine().split(" ");

        BiPredicate<String, Integer> isValid = (a, b) -> {
            int sum = 0;

            for (int i = 0; i < a.length(); i++) {
                sum += (int) a.charAt(i);
            }

            return sum >= b;
        };

        TriFunction<Integer, BiPredicate<String, Integer>, String[], String> motherFunction = (a, b, c) -> {
            String result = null;

            for (String name : c) {
                if (b.test(name, a)){
                    result = name;
                    break;
                }
            }

            return result;
        };

        System.out.println(motherFunction.apply(number, isValid, names));
    }
}
