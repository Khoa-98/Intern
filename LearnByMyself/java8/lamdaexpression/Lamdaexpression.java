package lamdaexpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javax.sound.sampled.SourceDataLine;

public class Lamdaexpression {
    @FunctionalInterface
    interface Sayable1 {
        public String say();

    }

    @FunctionalInterface
    interface Sayable2 {

        public String say(String name);
    }

    @FunctionalInterface
    interface Addable {
        int add(int a, int b);
    }

    public static void main(String[] args) {

        // before haveing java 8

        // List<String> languages = Arrays.asList("Java", "C#", "C++", "PHP",
        // "Javascript");

        // Collections.sort(languages, new Comparator<String>() {

        // @Override
        // public int compare(String o1, String o2) {
        // return o1.compareTo(o2);
        // }

        // });

        // for (String language : languages) {
        // System.out.println(language);
        // }

        // from java 8: had lamda Expression
        List<String> languages = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

        Collections.sort(languages, (o1, o2) -> o1.compareTo(o2));
        for (String language : languages) {
            System.out.println(language);
        }
        // lamda have no argument
        Sayable1 s = () -> {
            return "i dont have anything to say with you...";
        };
        System.out.println(s.say());

        // lamda have a argument
        Sayable2 s2 = (name) -> {
            return "hello " + name;
        };
        System.out.println(s2.say("khoa"));

        // shorter
        Sayable2 s3 = name -> "hello, " + name;
        System.out.println(s3.say("Khoa"));

        // lamda have multiple argument
        Addable a1 = (int a, int b) -> (a + b);
        System.out.println(a1.add(1, 3));

        // Lambda expression with multi-statement
        Addable a2 = (a, b) -> {
            int sum = a + b;
            return sum;
        };
        System.out.println(a2.add(2, 4));


        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Samsung A5", 17000f));
        list.add(new Product(3, "Iphone 6S", 65000f));
        list.add(new Product(2, "Sony Xperia", 25000f));
        list.add(new Product(4, "Nokia Lumia", 15000f));
        list.add(new Product(5, "Redmi4 ", 26000f));
        list.add(new Product(6, "Lenevo Vibe", 19000f));

        // using lambda to filter data
        Stream<Product> filter_datas = list.stream().filter(p -> p.price > 20000);
        filter_datas.forEach(product -> System.out.println(product.name + ": " + product.price));
    }

    
        
}
