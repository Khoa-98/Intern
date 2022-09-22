package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ConvertToStreamExxample2 {
    public static void main(String[] args) {
        
        // Generate Streams from Arrays using .stream or Stream.of
        String[] languages = { "Java", "C#", "C++", "PHP", "Javascript" };

        // Get Stream using the Arrays.stream
        Stream<String> testStream1 = Arrays.stream(languages);
        testStream1.forEach(x -> System.out.println(x));
        System.out.println();

        // Get Stream using the Stream.of
        Stream<String> testStream2 = Stream.of(languages);
        testStream2.forEach(x -> System.out.println(x));

        
        // Generate Streams from Collections
        System.out.println("Generate Streams from Collections");
        List<String> items = new ArrayList<>();
        items.add("Java");
        items.add("C#");
        items.add("C++");
        items.add("PHP");
        items.add("Javascript");

        items.stream().forEach(i -> System.out.println(i));

        
    }
}
