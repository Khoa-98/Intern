package stream;

import java.util.Arrays;
import java.util.List;

public class SortedStream {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

        // sorted according to natural order
        data.stream().sorted().forEach(System.out::println);
        System.out.println();

        // sorted according to the provided Comparator
        data.stream().sorted((o1, o2) -> o2.length() - o1.length()).forEach(System.out::println);

    }
}
