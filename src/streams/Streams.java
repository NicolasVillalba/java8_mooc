package streams;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by valenciap on 21/07/2015.
 */
public class Streams {

    public static void main(String[] args) {
        Streams streams = new Streams();
        streams.removeDuplicated();
        streams.showNotRepeated();
        streams.findKthElement();
        streams.testPairObject();
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
        Pair two = new Pair(1,2);
        System.out.println("Pair are equals: "+one.equals(two));
    }

}
