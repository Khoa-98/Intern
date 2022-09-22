package stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ErrorStream {
    public static void main(String[] args) {
        // Stream<String> stream = Stream.of("Java", "C#", "C++", "PHP", "Javascript") //
        //         .filter(s -> s.startsWith("J"));

        // stream.anyMatch(s -> true); // ok
        // stream.noneMatch(s -> true); // exception

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("Java", "C#", "C++", "PHP", "Javascript")
                .filter(s -> s.startsWith("J"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);
    }
        
    }

