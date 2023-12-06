package dev.yasint.week10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StreamsTest {

    @Test
    public void test() {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // Filter out the number which are divisible by 2;
        // And then, multiple all those filtered numbers by 2
        // And then limit the result for 4 elements in the final array.

        int[] newArr = new int[4];
        int count = 0;
        for (int j : arr) {
            if (j % 2 == 0) {
                newArr[count] = j * 2;
                count++;
                if (count == 4) break;
            }
        }

        System.out.println(Arrays.toString(newArr));

        assertArrayEquals(new int[]{4, 8, 12, 16}, newArr);

    }

    @Test
    public void streams() {

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // Filter out the number which are divisible by 2;
        // And then, multiply all those filtered numbers by 2
        // And then limit the result for 4 elements in the final array.

        Predicate<Integer> predicate = (i) -> i % 2 == 0;


        Stream<Integer> limit = Arrays.stream(arr)
                .filter(predicate)
                .map((number) -> number * 2)
                .limit(4);


        assertArrayEquals(
                new Integer[]{4, 8, 12, 16},
                limit.toArray(Integer[]::new)
        );

    }

    @Test
    public void example() {

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using Predicate<T>
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // Using Function<T, R>
        List<Integer> nameLengths = names
                .stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(nameLengths);

        // Using Consumer<T>
        names.forEach(name -> System.out.println("Hello, " + name));

    }

    @Test
    public void example2() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sequential Stream
        long count = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println("Even numbers (sequential): " + count);

        // Parallel Stream
        count = numbers.parallelStream().filter(n -> n % 2 == 0).count();
        System.out.println("Even numbers (parallel): " + count);

    }

    @Test
    public void example3() {

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Using Parallel Stream
        List<String> processedWords = words.parallelStream()
                .filter(w -> w.length() > 5)
                .map(String::toUpperCase)
                .toList();

        System.out.println("Processed words: " + processedWords);

    }

    @Test
    public void example4() {

        int[] arr = new int[1_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

//        int sum = 0;
//        for (int i = 0; i < arr.length; i++) {
//            sum += i;
//        }

        System.out.println(Arrays.stream(arr)
                .reduce(0, Integer::sum));
    }


    @Test
    public void example5() {

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // get the all the letters of these words.
        // count occurrence of each letter.
        // group them by the letter and specify the count.

        // { e: 3, p: 2 }
        // applebananacherrydate

        String reduce = words.stream()
                .reduce("",
                        (String prev, String current) -> prev + current);
        // Arrays.stream(reduce.toCharArray())

        // https://www.baeldung.com/java-difference-map-and-flatmap
        Map<Character, Long> letterCount = words
                .parallelStream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );

        System.out.println(letterCount);

    }

    @Test
    public void example6() {

        List<Integer> sharedList = new ArrayList<>();

        IntStream.range(0, 10).parallel().forEach((v) -> {
            sharedList.add(v);
        });

        System.out.println(sharedList);

        // Correct approach using a thread-safe collector
        List<Integer> correctList = IntStream.range(0, 10000).parallel().boxed().toList();
        System.out.println("Correctly populated list size: " + correctList);


    }


}
