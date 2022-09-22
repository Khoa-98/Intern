package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertFromStreanExample {
    public static void main(String[] args) {
        // stream -> collect
        Stream<String> stream = Stream.of("java", "php", "C", "html");
        List<String> languages = stream.collect(Collectors.toList());

        // stream -> Array
        Stream<String> stream2 = Stream.of("java", "php", "C", "html");
        String[] languages2 = stream2.toArray(String[]::new);

        System.out.println(languages);
        System.out.println(Arrays.toString(languages2));
    }

}
