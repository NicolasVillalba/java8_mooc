package streams;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by valenciap on 21/07/2015.
 */
public class Streams {

    public static void main(String[] args) throws Exception{
        Streams streams = new Streams();
        streams.removeDuplicated();
        streams.showNotRepeated();
        streams.findKthElement();
        streams.testPairObject();
        streams.getMode();
        streams.sortNumbers();
    }

    private void removeDuplicated() {
        Integer[] numbers = {1,3,3,3,1,5,6,7,8,1};
        Arrays.asList(numbers)
                .stream()
                .distinct()
                .collect(java.util.stream.Collectors.toList())
                .forEach(System.out::println);
    }

    private void showNotRepeated() {
        final String sentence = "the quick brown fox jumps then quickly blow air";

        Function<String, String> filterOnlyOne = (String s) ->  {
            StringBuilder filtered = new StringBuilder();
            HashMap<Character, Boolean> mapChar = new HashMap<>();

            s.chars().mapToObj(c -> ((char) c)).forEach(c -> mapChar.put(c,mapChar.containsKey(c)));

            mapChar.entrySet().stream().forEach(v -> {
                if(!v.getValue()) {
                    filtered.append(v.getKey());
                }
            });

            return filtered.toString();
        };
        System.out.println(filterOnlyOne.apply(sentence));
    }

    private void findKthElement() {
        int[] list = {7,3,9,2,4,6};

        IntStream.of(list).sorted().skip(3).limit(1).forEach(System.out::println);
    }

    private void testPairObject() {
        Pair one = new Pair(1,1);
        Pair two = new Pair(1,1);
        System.out.println("Pair are equals: "+one.equals(two));
    }

    private void getMode() {
        int[] numbers = {3,4,7,6,3,6,8,5,7,2,5,4,6,5,8,3,5,6,8,3,6};
        HashMap<Integer, Integer> numbers_repetition = new HashMap<>();
        int maxNumberRepeated = Integer.MIN_VALUE;
        for(int i = 0; i < numbers.length; i++) {
            if(numbers_repetition.containsKey(numbers[i])) {
                int number = numbers[i];
                int repetitions = numbers_repetition.get(number)+1;
                numbers_repetition.put(number,repetitions);

            } else {
                numbers_repetition.put(numbers[i],1);
            }
            if(!numbers_repetition.containsKey(maxNumberRepeated) ||
                    (numbers_repetition.containsKey(maxNumberRepeated) &&
                    numbers_repetition.get(numbers[i]) > numbers_repetition.get(maxNumberRepeated))) {
                maxNumberRepeated = numbers[i];
            }
        }
        System.out.println("Mode is: "+maxNumberRepeated);
    }

    private void sortNumbers() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("C:\\Users\\valenciap\\Downloads\\IntegerArray.txt"), StandardCharsets.UTF_8)) {
      /* YOUR CODE HERE */
            long before = System.currentTimeMillis();
            List<String> result = reader
                    .lines()
                    .map(number -> Integer.parseInt(number))
                    .sorted()
                    .map(number -> number.toString())
                    .collect(Collectors.toList());
            long after = System.currentTimeMillis();
            System.out.println("Result: "+ result);
            System.out.println("Time elapsed: "+ (after - before));

            Files.write(Paths.get("C:\\Users\\valenciap\\Downloads\\SortedIntegerArray.txt"),result);

        }
    }

}
