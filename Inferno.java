import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class Inferno {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<Integer, Integer> isValid = (a, b) -> a == b;

        BiFunction<Integer, int[], Integer> startNumberFunc = (a, b) -> {
            if (a < 0){
                return 0;
            } else {
                return b[a];
            }
        };

        BiFunction<Integer, int[], Integer> endNumberFunc = (a, b) -> {
            if (a > b.length - 1){
                return 0;
            } else {
                return b[a];
            }
        };

        int[] numbers = Arrays.stream(bfr.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] inputArgs = bfr.readLine().split(";");
        List<Integer> markedGems = new ArrayList<>();

        while (!inputArgs[0].equals("Forge")){
            String command = inputArgs[0];
            String[] filterTypes = inputArgs[1].split(" ");
            int filterParameter = Integer.parseInt(inputArgs[2]);

            if (command.equals("Exclude")){
                int sum = 0;

                if (filterTypes.length > 2){
                    for (int i = 0; i < numbers.length; i++) {
                        sum += numbers[i] + startNumberFunc.apply(i - 1, numbers) + endNumberFunc.apply(i + 1, numbers);

                        if (isValid.test(sum, filterParameter)){
                            markedGems.add(i);
                        }

                        sum = 0;
                    }
                } else if (filterTypes[1].equals("Left")){
                    for (int i = 0; i < numbers.length; i++) {
                        sum += numbers[i] + startNumberFunc.apply(i - 1, numbers);

                        if (isValid.test(sum, filterParameter)){
                            markedGems.add(i);
                        }

                        sum = 0;
                    }
                } else {
                    for (int i = 0; i < numbers.length; i++) {
                        sum += numbers[i] + endNumberFunc.apply(i + 1, numbers);

                        if (isValid.test(sum, filterParameter)){
                            markedGems.add(i);
                        }

                        sum = 0;
                    }
                }
            } else {
                if (markedGems.size() > 0){
                    markedGems.remove(markedGems.size() - 1);
                }
            }

            inputArgs = bfr.readLine().split(";");
        }

        for (int i = 0; i < numbers.length; i++) {
            if (!markedGems.contains(i)){
                System.out.printf("%d ", numbers[i]);
            }
        }
    }
}
