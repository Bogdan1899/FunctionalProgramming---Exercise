import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class PartyReservation {
    public static void main(String[] args) throws IOException {

        BiPredicate<String, String> isStartingWith = String::startsWith;

        BiPredicate<String, String> isEndingWith = String::endsWith;

        BiPredicate<String, Integer> isWithLength = (a, b) -> a.length() == b;

        BiPredicate<String, String> isContainingWith = String::contains;

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        List<String> removedNames = new ArrayList<>();
        String[] names = bfr.readLine().split(" ");
        String[] inputArgs = bfr.readLine().split(";");

        while (!inputArgs[0].equals("Print")){
            String command = inputArgs[0];
            String filterType = inputArgs[1];
            String filterParameter = inputArgs[2];

            if (command.contains("Add")){
                switch (filterType.toLowerCase()){
                    case "starts with":
                        for (String name : names) {
                            if (isStartingWith.test(name, filterParameter)){
                                removedNames.add(name);
                            }
                        }

                        break;
                    case "ends with":
                        for (String name : names) {
                            if (isEndingWith.test(name, filterParameter)){
                                removedNames.add(name);
                            }
                        }

                        break;
                    case "length":
                        for (String name : names) {
                            if (isWithLength.test(name, Integer.parseInt(filterParameter))){
                                removedNames.add(name);
                            }
                        }

                        break;
                    case "contains":
                        for (String name : names) {
                            if (isContainingWith.test(name, filterParameter)){
                                removedNames.add(name);
                            }
                        }

                        break;
                }
            } else {
                switch (filterType.toLowerCase()){
                    case "starts with":
                        for (int i = 0; i < removedNames.size(); i++) {
                            if (isStartingWith.test(removedNames.get(i), filterParameter)){
                                removedNames.remove(removedNames.get(i));
                                i--;
                            }
                        }

                        break;
                    case "ends with":
                        for (int i = 0; i < removedNames.size(); i++) {
                            if (isEndingWith.test(removedNames.get(i), filterParameter)){
                                removedNames.remove(removedNames.get(i));
                                i--;
                            }
                        }

                        break;
                    case "length":
                        for (int i = 0; i < removedNames.size(); i++) {
                            if (isWithLength.test(removedNames.get(i), Integer.parseInt(filterParameter))){
                                removedNames.remove(removedNames.get(i));
                                i--;
                            }
                        }

                        break;
                    case "contains":
                        for (int i = 0; i < removedNames.size(); i++) {
                            if (isContainingWith.test(removedNames.get(i), filterParameter)){
                                removedNames.remove(removedNames.get(i));
                                i--;
                            }
                        }

                        break;
                }
            }

            inputArgs = bfr.readLine().split(";");
        }

        for (String name : names) {
            if (!removedNames.contains(name)){
                System.out.printf("%s ", name);
            }
        }
    }
}
